//
//  Module.m
//  YaMi
//
//  Created by BJCA on 2018/5/15.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import "Module.h"

static NSArray* meal;
static NSArray* add;
static NSArray* restaurant;
static NSString* choosedMeal;
static NSString* choosedAdd;
@implementation Module{
}

+ (void)initWithData:(NSDictionary*)data{
    meal = [[NSArray alloc]initWithArray: [data valueForKey:@"meal"]];
    add = [[NSArray alloc]initWithArray:[data valueForKey:@"add"]];
    restaurant = [[NSArray alloc]initWithArray:[data valueForKey:@"restaurant"]];
}
+ (NSArray*)getMealForDay:(int)day{
    NSArray* meals = [[NSArray alloc]initWithArray:meal[day-1]];
    return meals;
}
+ (NSArray*)getAddForDay:(int)day{
    NSArray* adds = [[NSArray alloc]initWithArray:add[day-1]];
    return adds;
}
+ (NSString*)getRestaurantForDay:(int)day{
    NSString* restaurants = [[NSString alloc]initWithString:restaurant[day-1]];
    return restaurants;
}
+ (void)setMeal:(NSString*)selectedMeal{
    choosedMeal = selectedMeal;
}
+ (void)setAdd:(NSString*)selectedAdd{
    choosedAdd = selectedAdd;
}
+ (NSString*)getMeal{
    return choosedMeal;
}
+ (NSString*)getAdd{
    return choosedAdd;
}
@end
