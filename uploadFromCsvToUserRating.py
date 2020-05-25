import csv

from pymongo import MongoClient
import pandas as pd
import json
import numpy as np
from pprint import pprint
user = pd.read_csv('users_data.csv',sep=',')
user.dropna(inplace=True)
list_to_rename = ['username','hotel_name','score']

dictionary = {}
df = pd.DataFrame()
newKey = ''
for index in range(0, len(user)):
    dictionary = {}
    for column in user.columns:
        dictionary[column] = user.iloc[index,:][column]
    df = df.append(dictionary, ignore_index=True, sort=None)

# Making a Connection with MongoClient
client = MongoClient("mongodb://aym:root@localhost:27017/?authSource=CodePhenomRemastered")
# database
db = client["HotelRecommenderSystem"]
hotel= db["UserRating"]
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