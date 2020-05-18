import csv

from pymongo import MongoClient
import pandas as pd
import json
import numpy as np
from pprint import pprint
hotels0 = pd.read_csv('Hotels.csv',sep=',')
hotels0.dropna(inplace=True)
list_to_rename = ['cleanliness_score','hotel_class','hotel_score_reviews','hotel_style','location','location_score','property_amenties','room_features','service_score','value_score']
list_of_column = ['Cleanliness_score','Hotel_class','Hotel_score_reviews','Hotel_style','Location','Location_score','Property_amenties','Room_features', 'Service_score','Value_score']
dictionary = {}
df = pd.DataFrame()
newKey = ''
for indexHotel in range(0, len(hotels0)):
    #print("Index is : ",indexHotel)
    if indexHotel > 50:
        break;
    for column in hotels0.columns:
        if(column in list_to_rename):
            indexColumn = list_to_rename.index(column)
            newKey = list_of_column[indexColumn]
        else :
            newKey = column
        if(column in {'url','totalReview'}):
            continue
        elif(column != 'property_amenties' and column != 'room_features' and column != 'hotel_style'):
            if column in {'price','hotel_score_reviews','location_score','cleanliness_score','service_score','value_score'}:
                #dic = {'$numberDouble',hotels0.iloc[indexHotel,:][column]}
                dictionary[newKey] = hotels0.iloc[indexHotel,:][column]
            else :
                dictionary[newKey] = hotels0.iloc[indexHotel,:][column]
        else:
            propertyArray = []
            #print("column : ",column)
            #print(hotels0.iloc[indexHotel,:][column])
            for index_property in hotels0.iloc[indexHotel,:][column].split(" "):
                propertyArray.append(index_property)
                dictionary[newKey] = propertyArray
    df = df.append(dictionary, ignore_index=True, sort=None)

# Making a Connection with MongoClient
#client = MongoClient("mongodb://aym:root@localhost:27017/?authSource=CodePhenomRemastered")

client = MongoClient("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false")
#client = MongoClient("mongodb://aym:root@localhost:27017/?authSource=CodePhenomRemastered")
# database
db = client["HotelRecommenderSystem"]
hotel= db["Hotel"]
df = df.drop_duplicates(subset='name', keep="first")
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
