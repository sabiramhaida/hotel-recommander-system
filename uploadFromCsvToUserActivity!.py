import csv

from pymongo import MongoClient
import pandas as pd
import json
import numpy as np
from datetime import datetime
from pprint import pprint
import random 
import time

user = pd.read_csv('users_data.csv',sep=',')
user.dropna(inplace=True)
list_to_rename = ['username','hotel_name','score']

dictionary = {}
df = pd.DataFrame()
newKey = ''
for index in range(0, len(user)):
    if(index > 100):
        break;
    dictionary = {}
    for column in user.columns:
        dictionary[column] = user.iloc[index,:][column]
    df = df.append(dictionary, ignore_index=True, sort=None)


def str_time_prop(start, end, format, prop):
    stime = time.mktime(time.strptime(start, format))
    etime = time.mktime(time.strptime(end, format))
    ptime = stime + prop * (etime - stime)
    return time.strftime(format, time.localtime(ptime))

def random_date(start, end, prop):
    return str_time_prop(start, end, '%Y-%m-%d %H:%M:%S', prop)
#Return a random date timestamp
def random_date_hotel():
  return datetime.strptime(random_date("2015-05-22 22:11:44", "2020-05-22 22:11:44", random.random()),'%Y-%m-%d %H:%M:%S')

df = df.rename(columns={"score": "activity"})
df["activity"] = "view"
df['date'] = pd.Series([random_date_hotel() for i in range(0,len(df))])

# Making a Connection with MongoClient
client = MongoClient("mongodb://aym:root@localhost:27017/?authSource=CodePhenomRemastered")
# database
db = client["HotelRecommenderSystem"]
hotel= db["UserActivity"]
records = json.loads(df.T.to_json()).values()
hotel.insert(records)

#df.to_json('test.json', orient='records')

#mongoimport --db HotelRecommenderSystem --collection Hotel --authenticationDatabase CodePhenomRemastered --username aym --password root --drop  --file /home/aym/Documents/test.json







"""

client = MongoClient("mongodb://aym:root@localhost:27017/?authSource=CodePhenomRemastered")
db=client.HotelRecommenderSystem

serverStatusResult=db.command("serverStatus")

feature_names_colab = ['name','Country','Region','Street','Zip','property_amenties','room_features','hotel_style','hotelclass','price','hotel_score_reviews'
           ,'location_score','cleanliness_score','service_score','value_score']
feature_names_mongo = ['name','Country','Region','Street','Zip','Property_amenties','Room_features'
                       ,'Hotel_style','Hotel_class','Price','Hotel_score_reviews','Location_score','Cleanliness_score'
                       ,'Service_score','Value_score']
list = []
data_dictionnary = {}
hotels_data = db.Hotel
for index,data in enumerate(hotels_data.find()):
    data_dictionnary = {}
    for index1 in range(0,15):
        data_dictionnary[feature_names_colab[index1]] = data[feature_names_mongo[index1]]
        list.append(data_dictionnary)

df = pd.DataFrame(list)
print(list)
"""