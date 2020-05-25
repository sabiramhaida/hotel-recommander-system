import pandas as pd
from scipy.spatial.distance import cosine
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.feature_extraction.text import CountVectorizer
from pymongo import MongoClient
import numpy as np
import sys
from fuzzywuzzy import fuzz

"""Import data from mongodb"""
client = MongoClient("mongodb://aym:root@localhost:27017/?authSource=CodePhenomRemastered")
db=client.HotelRecommenderSystem

feature_names_colab = ['name','Country','Region','Street','Zip','property_amenties','room_features','hotel_style','hotel_class','price','hotel_score_reviews'
           ,'location_score','cleanliness_score','service_score','value_score','location']
feature_names_mongo = ['name','Country','Region','Street','Zip','Property_amenties','Room_features'
                       ,'Hotel_style','Hotel_class','price','Hotel_score_reviews','Location_score','Cleanliness_score'
                       ,'Service_score','Value_score','Location']
list = []
data_dictionnary = {}
hotels_data = db.Hotel
for index,data in enumerate(hotels_data.find()):
    data_dictionnary = {}
    for index1 in range(0,16):
        data_dictionnary[feature_names_colab[index1]] = data[feature_names_mongo[index1]]
        list.append(data_dictionnary)

hotels = pd.DataFrame(list)

for column in ['property_amenties','room_features','hotel_style'] :
    new_properties = ""
    for index_row, row in enumerate(hotels[column]):
        for index, properties in enumerate(row):
            if (index != len(row)):
                new_properties += properties + " "
            else:
                new_properties += properties
        hotels[column][index_row] = new_properties



#drop rows with missing values ....
hotels.dropna(how="any")
hotels = hotels.drop_duplicates(subset='name', keep="first")

hotels['property_amenties'] = hotels['property_amenties'].apply(lambda x: x.split(' '))

list_of_properties = hotels.property_amenties

#check which unique amenities exist in highlights column
list_of_unique_properties = []
for list in list_of_properties:
    for pr in list:
        if pr not in list_of_unique_properties:
            list_of_unique_properties.append(pr)

def reOrdering_properties(original_list):
    arr = []
    for pr in list_of_unique_properties:
        if pr in original_list:
            arr.append(pr)
        else:
            arr.append('')
    return arr

hotels['property_amenties'] = hotels.property_amenties.apply(lambda hotel_row: reOrdering_properties(hotel_row))

hotels.values

property_amen_df = pd.DataFrame()

property_amen_df[list_of_unique_properties] = pd.DataFrame([x for x in hotels.property_amenties])

property_amen_df.shape

property_amen_df.head(5)

property_amen_df = property_amen_df.applymap(lambda x: 1 if x != '' else 0)

property_amen_df.head(5)

hotels = hotels[hotels.hotel_class.notnull()].reset_index()

hotels = pd.concat([hotels, property_amen_df], axis=1)

hotels.shape

hotels.hotel_class.isnull().sum()


hotels.drop(['location','property_amenties'], axis=1, inplace=True)

"""## hotel style"""

hotels.hotel_style

hotels['hotel_style'] = hotels['hotel_style'].astype(str)

hotels['hotel_style'] = hotels['hotel_style'].apply(lambda x: x.split(' '))

list_of_hotels_style = hotels.hotel_style

#getting unique styles that exist in hotel_style column
list_of_unique_hotel_style = []
for list in list_of_hotels_style:
    for pr in list:
        if pr not in list_of_unique_hotel_style and pr != '':
            list_of_unique_hotel_style.append(pr)

def reOrdering_styles(original_list):
    arr = []
    for pr in list_of_unique_hotel_style:
        if pr in original_list:
            arr.append(pr)
        else:
            arr.append('')
    return arr

hotels['hotel_style'] = hotels.hotel_style.apply(lambda hotel_row: reOrdering_styles(hotel_row))

hotel_styl_df = pd.DataFrame()

hotel_styl_df[list_of_unique_hotel_style] = pd.DataFrame([x for x in hotels.hotel_style])

hotel_styl_df = hotel_styl_df.applymap(lambda x: 1 if x != '' else 0)

hotel_styl_df.head(1)

hotel_styl_df.shape

hotels = pd.concat([hotels, hotel_styl_df], axis=1)

"""# Working on Data"""
# Useless cell for now until we want to work on qualitative features
def get_qual_matrix(dataframe):
    cv = CountVectorizer()
    hotels_desc = []
    for index, row in dataframe.iterrows():
      desc = str(row['property_amenties'])+' '+str(row['room_features'])+' ' +str(row['hotel_style'])
      hotels_desc.append(desc)
    cv = CountVectorizer()
    temp = cv.fit_transform(Hotels_desc)
    sparse_matrix = list(map(lambda row:row.tolist(),features_matrix.toarray()))
    return(sparse_matrix)

def getFeatures(dataframe):
  quantit_features = list()
  hotel_faetures_matrix = get_qual_matrix(dataframe)
  for index,row in dataframe.iterrows():
    quantit_features = [row['hotel_score_reviews'],row['price'],row['hotel_class']]
    hotel_faetures_matrix[index] = hotel_faetures_matrix[index] + quantit_features
  return hotel_faetures_matrix

##cleaninig
def clean_dataset(df):
    assert isinstance(df, pd.DataFrame), "df needs to be a pd.DataFrame"
    df.dropna(inplace=True)
    indices_to_keep = ~df.isin([np.nan, np.inf, -np.inf]).any(1)
    return df[indices_to_keep].astype(np.float64)


country = sys.argv[2]



hotels = hotels[hotels['Country'] == country]
hotels = hotels.drop_duplicates(subset="name")



hotels_newdf = hotels.iloc[:,8:]
hotels_newdf


hotel_names = pd.DataFrame()
hotel_names[['name']] = pd.DataFrame(hotels.name)
hotel_names.shape




clean_dataset(hotels_newdf)
hotels_newdf.head(5)

prep = hotels_newdf.values.tolist()
similarity_scores = cosine_similarity(prep)




def fuzzy_matcher(mapper, inputHotel):
    match_tuple = []
    for name, idx in mapper.items():
        ratio = fuzz.ratio(name.lower(), inputHotel.lower())
        if ratio >= 20:
            match_tuple.append((name, idx, ratio))
    # sort
    match_tuple = sorted(match_tuple, key=lambda x: x[2])[::-1]
    if not match_tuple:
        return
    return match_tuple[0][1]

def content_based_recomm(similarity_table, mapper, numberOfRecommandations, inputHotel):

      idx = fuzzy_matcher(mapper, inputHotel)
      similair_hotels_scores = similarity_scores[idx]

      indices = [i for i in range(len(similair_hotels_scores.tolist()))]

      similair_hotels = sorted(zip(indices, similair_hotels_scores.tolist()), key=lambda x: x[1])[:0:-1][:numberOfRecommandations]

      reverse_mapper = {v: k for k, v in mapper.items()}

      i=0
      for idx,score in similair_hotels :
          print(reverse_mapper[idx])
          i+=1

mapper = {
    hotel : i
    for i, hotel in enumerate(hotel_names.name)
}

if __name__== "__main__":
    content_based_recomm(similarity_table=similarity_scores,
                         mapper=mapper,
                         inputHotel=sys.argv[1],
                         numberOfRecommandations=7)


