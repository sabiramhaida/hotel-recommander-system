import os

from selenium.webdriver.support import expected_conditions as EC

from selenium import webdriver
from bs4 import BeautifulSoup
import requests
import pandas as pd
from selenium import webdriver
from selenium.common.exceptions import TimeoutException
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
import csv
import json

delay = 2


# pour trouver les url des hotels qui existent dans
def getUniqueUrls(filePath):
    data = pd.read_excel(filePath)
    return (data.taObjectUrl.unique())


def getCLass(array_of_classes):
    classString = ""
    for index, i in enumerate(range(len(array_of_classes))):
        if (index != (len(array_of_classes) - 1)):
            classString = classString + array_of_classes[i] + " "
        else:
            classString = classString + array_of_classes[i]
    return classString

def getUserDate(soup,hotel_name):
    for a in soup.find_all("div","hotels-community-tab-common-Card__card--ihfZB hotels-community-tab-common-Card__section--4r93H"):
        user_review_hotel = {}
        user_review_hotel['username']  = a.find("a",'ui_header_link social-member-event-MemberEventOnObjectBlock__member--35-jC').get_text();
        user_review_hotel['hotel_name'] = hotel_name

        if(a.find("span","ui_bubble_rating bubble_50")):
            user_review_hotel['score'] = 5
        elif(a.find("span","ui_bubble_rating bubble_40")):
            user_review_hotel['score'] = 4
        elif (a.find("span", "ui_bubble_rating bubble_30")):
            user_review_hotel['score'] = 3
        elif(a.find("span","ui_bubble_rating bubble_20")):
            user_review_hotel['score'] = 2
        else:
            user_review_hotel['score'] = 1


        csv_columns = ['username', "hotel_name","score"]
        csv_file_user = "users_data.csv"
        try:
            if os.path.isfile('users_data.csv'):
                file_exist = True
            else :
                file_exist = False
            with open(csv_file_user, 'a') as csvfile_user:
                writer = csv.DictWriter(csvfile_user, fieldnames=csv_columns)
                if(file_exist == False):
                    writer.writeheader()
                writer.writerow(user_review_hotel)
        except IOError:
            print("I/O error")
    return 0

def getData(hotel_url):
    print(hotel_url,"\n")
    price = 0
    property_amenties = ""
    room_features = ""
    hotel_style = ""
    location = ""
    hotel_score_reviews = ""
    hotel_class = 0
    hotel_name = ""
    country = ""
    region = ""
    postalCode = 0
    totaleReview = 0
    hotel_profile = {}  # dict de données de l'hotel courant

    browser.get(hotel_url)

    content = browser.page_source
    soup = BeautifulSoup(content, "html.parser")

    if soup.find("div", {"class": "hotels-hotel-review-about-addendum-AddendumItem__content--iVts5"}):
        info_price_exist = 0
        for a in soup.find_all("div", {"class": "hotels-hotel-review-about-addendum-AddendumItem__title--2QuyD"}):
            if(a.get_text() == "PRICE RANGE"):
                info_price_exist = 1;
        if(info_price_exist == 0):
            return None

        price = soup.find("div", class_="hotels-hotel-review-about-addendum-AddendumItem__content--iVts5").get_text()
        minprice = price.split()[1]
        if("," in minprice):
            minprice = minprice.split(",")
            minprice = minprice[0]+minprice[1]

        maxprice = price.split()[4]
        if("," in maxprice):
            maxprice = maxprice.split(",")
            maxprice = maxprice[0]+maxprice[1]
        if(maxprice.isdigit() & minprice.isdigit()):
            price = (int(minprice)+int(maxprice))/2
            print("the price is ", price)
            hotel_profile["price"] = price
        else :
            print("in the price")
            return None
    # Property amenities
    for a in soup.find_all("div", class_="hotels-hr-about-amenities-Amenity__amenity--3fbBj"):
        if (a.parent.previous_sibling.get_text() == "Property amenities"):
            property_amenties = property_amenties +'_'.join(a.get_text().replace('/','').split())+" "
            hotel_profile["property_amenties"] = property_amenties

    # Room feature
    for a in soup.find_all("div", class_="hotels-hr-about-amenities-Amenity__amenity--3fbBj"):
        if (a.parent.previous_sibling.get_text() == "Room features"):
            room_features = room_features +'_'.join(a.get_text().replace('/','').split())+" "
            hotel_profile["room_features"] = room_features

    # location
    for index,a in enumerate(soup.find_all("div",class_="hotels-hotel-review-about-addendum-AddendumItem__content--iVts5")):
        if(index == 0):
            location = a.get_text();
            hotel_profile["location"] = location

    if soup.find("div", {"class": "public-business-listing-ContactInfo__offer--KAFI4 public-business-listing-ContactInfo__location--1jP2j"}):
        location = soup.find("div", class_="public-business-listing-ContactInfo__offer--KAFI4 public-business-listing-ContactInfo__location--1jP2j").get_text()
        hotel_profile["location"] = location
    else :
        print("in the location")
        return  None

    # hotel_score
    if soup.find("span", {"class": "hotels-hotel-review-about-with-photos-Reviews__overallRating--vElGA"}):
        hotel_score_reviews = soup.find("span", class_="hotels-hotel-review-about-with-photos-Reviews__overallRating--vElGA").get_text()
        hotel_profile["hotel_score_reviews"] = hotel_score_reviews
    else:
        print("in the score")
        return None

    for index, a in enumerate(soup.find_all("div", class_="hotels-hr-about-layout-TextItem__textitem--2JToc")):
        classString = getCLass(a.parent['class'])
        if (classString == "ui_column is-6" and index != 0):
            hotel_style = hotel_style +'_'.join(a.get_text().replace('/','').split())+" "
            hotel_profile["hotel_style"] = hotel_style

    # Hotel class
    if soup.find("span", {"class": "hotels-hotel-review-about-with-photos-goodtoknow-StarRating__stars--3tYZG"}):
        div_class = getCLass(soup.find("span", class_="hotels-hotel-review-about-with-photos-goodtoknow-StarRating__stars--3tYZG").find("span")['class'])
        if (div_class == "_2TmwtWEr f33bWmtw uq1qMUbD"):
            hotel_class = 5
        elif (div_class == "_2TmwtWEr _2MgVjxWG uq1qMUbD"):
            hotel_class = 2
        elif (div_class == "_2TmwtWEr _3RprXHxE uq1qMUbD"):
             hotel_class = 3
        elif (div_class == "_2TmwtWEr _30WZSV_9 uq1qMUbD"):
            hotel_class = 4
        elif (div_class == "_2TmwtWEr _2LYcDtDf uq1qMUbD"):
             hotel_class = 4.5
        else: hotel_class = 1
        hotel_profile["hotel_class"] = hotel_class
    else :
        print("In he class")
        return None

    # country, region, zip, name, url,...
    for l in soup.find_all('script', attrs={"type": "application/ld+json"}):
        l = str(l)
        first = l.find('>')
        last = l.rfind('<')
        l = l[first + 1:last]
        data = str(l).strip()
        data = json.loads(data)
        hotel_profile["name"] = data["name"]
        hotel_profile["url"] = "https://www.tripadvisor.in" + data["url"]
        hotel_profile["totalReview"] = data["aggregateRating"]["reviewCount"]
        hotel_profile["Street"] = data["address"]["streetAddress"]
        #Region
        found = 0
        for index, a in enumerate(soup.find_all("div", class_="hotels-hotel-review-about-addendum-AddendumItem__content--iVts5")):
            if (a.previous_sibling.get_text() == "LOCATION"):
                found = 1
                hotel_profile["Region"] = a.get_text(separator=' ')
        if(found == 0):
            return  None
        #
        hotel_profile["Zip"] = data["address"]["postalCode"]
        hotel_profile["Country"] = data["address"]["addressCountry"]["name"]
        # Get user data
        getUserDate(soup,data["name"])
        #
        break
    return hotel_profile


# ------------------------------------------------------------

browser = webdriver.Firefox(executable_path="/home/sabir/Documents/geckodriver-v0.26.0-linux64/geckodriver")

Hotels_url = getUniqueUrls("testF.xlsx")
headers = requests.utils.default_headers()
headers.update({'User-Agent': 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:52.0) Gecko/20100101 Firefox/52.0'})

Hotels_url = getUniqueUrls("testF.xlsx")


# csv columns
csv_columns = ['name', 'url', 'Country', 'Region', 'Street', 'Zip', 'location', 'property_amenties', 'room_features',
               'hotel_style', 'hotel_class', 'price', 'hotel_score_reviews', 'totalReview', ]

# la liste qui va contenir le dict de données des hotels
dict_data = list()


# --
i = 0;
for index,hotel_url in enumerate(Hotels_url):
    if ("Hotel_Review" in hotel_url):

        if (i > 70): break
        hotel_profile = getData(hotel_url)
        print("proceeded")
        if( not (hotel_profile is None)):
            i += 1;
            print(i,"\n")
            dict_data.append(hotel_profile)

csv_file = "Hotels.csv"
try:
    with open(csv_file, 'w') as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=csv_columns)
        writer.writeheader()
        for data in dict_data:
            writer.writerow(data)
except IOError:
    print("I/O error")
