//
//  ViewController.m
//  YaMi
//
//  Created by BJCA on 2018/5/7.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import "ViewController.h"
#import "LoopBanner.h"
#import "orderViewController.h"
@implementation ViewController{
    LoopBanner* loop;
}
- (IBAction)enter:(UIButton *)sender {
    //press the enter button
    if (loop.getMiddle == [UIColor blueColor]) {
        //enter order view
        UIStoryboard *sb = [UIStoryboard storyboardWithName:@"Order" bundle:nil];
        orderViewController* orderView = [sb instantiateViewControllerWithIdentifier:@"orderViewController"];
        self.navigationController.navigationBarHidden = NO;
        //[self.navigationController pushViewController:orderView animated:NO];
        [self presentViewController:orderView animated:NO completion:nil];
        orderView.navigationItem.title = @"今日美味";
    }
    NSLog(@"enter");
    return;
}

-(void)viewDidLoad{
    NSLog(@"viewController");
    [super viewDidLoad];
    //do things after view have been loaded
    
    //get screen size
    screenWidth = [UIScreen mainScreen].bounds.size.width;
    screenHeight = [UIScreen mainScreen].bounds.size.height;
    NSLog(@"screen's width is %d,and it's height is %d",(int)screenWidth,(int)screenHeight);
    loop = [[LoopBanner alloc] initWithFrame:CGRectMake(-0*screenWidth, 0.1*screenHeight, 1*screenWidth, 0.8*screenHeight)];
    [self.view addSubview:loop];
}
@end
