//
//  AppDelegate.h
//  YaMi
//
//  Created by BJCA on 2018/5/7.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "NavigationController.h"

@interface AppDelegate : UIResponder <UIApplicationDelegate>{
    NavigationController* navContrl;
}

@property (strong, nonatomic) UIWindow *window;


@end

