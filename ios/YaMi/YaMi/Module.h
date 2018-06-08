//
//  Module.h
//  YaMi
//
//  Created by BJCA on 2018/5/15.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Module : NSObject{
}
+ (void)initWithData:(NSDictionary*)data;
+ (NSArray*)getMealForDay:(int)day;
+ (NSArray*)getAddForDay:(int)day;
+ (NSString*)getRestaurantForDay:(int)day;
+ (void)setMeal:(NSString*)selectedMeal;
+ (void)setAdd:(NSString*)selectedAdd;
+ (NSString*)getMeal;
+ (NSString*)getAdd;
@end
