//
//  AppDelegate.m
//  YaMi
//
//  Created by BJCA on 2018/5/7.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import "AppDelegate.h"
#import "Module.h"
#import "httpController.h"

@interface AppDelegate (){
    
}

@end

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    // Override point for customization after application launch.
    NSLog(@"launch");
    /*navContrl = [[NavigationController alloc]init];
    self.window.rootViewController = navContrl;    
    //navContrl.navigationBarHidden = YES;
    UIStoryboard *sb = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
    ViewController *mainControl = sb.instantiateInitialViewController;
    [navContrl pushViewController:mainControl animated:NO];*/
    [[[httpController alloc]init] getDataFromUrl:@"https://www.baidu.com"];
    NSMutableArray* add = [[NSMutableArray alloc]init];
    NSMutableArray* meal = [[NSMutableArray alloc]init];
    NSMutableArray* restaurant = [[NSMutableArray alloc]init];
    for (int i = 1; i<6; i++) {
        NSMutableArray* mealForDay = [[NSMutableArray alloc]init];
        NSMutableArray* addForDay = [[NSMutableArray alloc]init];
        NSString* restaurantForDay = [[NSString alloc]initWithFormat:@"星期%i，餐厅",i];
        for (int j = 0; j<5; j++) {
            [mealForDay addObject:[NSString stringWithFormat:@"星期%i，套餐%i",i,j]];
        }
        for (int j = 0; j<4; j++) {
            [addForDay addObject:[NSString stringWithFormat:@"星期%i，加料%i",i,j]];
        }
        [meal addObject:mealForDay];
        [add addObject:addForDay];
        [restaurant addObject:restaurantForDay];
        
    }
    NSMutableDictionary* data = [[NSMutableDictionary alloc]init];
    [data setValue:meal forKey:@"meal"];
    [data setValue:add forKey:@"add"];
    [data setValue:restaurant forKey:@"restaurant"];
    NSLog(@"%@",data);
    [Module initWithData:data];
    NSLog(@"%@",[Module getRestaurantForDay:1]);
    return YES;
}


- (void)applicationWillResignActive:(UIApplication *)application {
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
    NSLog(@"active");
}


- (void)applicationDidEnterBackground:(UIApplication *)application {
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
    NSLog(@"background");
}


- (void)applicationWillEnterForeground:(UIApplication *)application {
    // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
    NSLog(@"foreground");
}


- (void)applicationDidBecomeActive:(UIApplication *)application {
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
    NSLog(@"active2");
}


- (void)applicationWillTerminate:(UIApplication *)application {
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}


@end
