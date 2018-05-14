//
//  submitViewController.m
//  YaMi
//
//  Created by BJCA on 2018/5/14.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import "submitViewController.h"

@interface submitViewController ()

@end

@implementation submitViewController
- (IBAction)goBackToOrder:(UIBarButtonItem *)sender {
    [self dismissViewControllerAnimated:NO completion:nil];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    NSString* endTimeStr = @"18:00:00";
    NSDateFormatter* dateFormatter = [[NSDateFormatter alloc]init];
    [dateFormatter setTimeZone:[NSTimeZone localTimeZone]];
    [dateFormatter setDateFormat:@"HH:mm:ss"];
    NSDate* endTime = [dateFormatter dateFromString:endTimeStr];
    NSLog(@"%@",endTime);
    // Do any additional setup after loading the view.
    NSLog(@"%@", [self getTimeToEnd:endTime]);
}

- (NSString*)getTimeToEnd:(NSDate*)endTime{
    NSString* timeSp;
    NSDateFormatter* formatter = [[NSDateFormatter alloc]init];
    [formatter setDateStyle:NSDateFormatterMediumStyle];
    [formatter setTimeStyle:NSDateFormatterShortStyle];
    NSLog(@"%@",[NSTimeZone localTimeZone]);
    NSTimeZone* timeZone = [NSTimeZone timeZoneWithName:@"Asia/Shanghai"];
    [formatter setTimeZone:[NSTimeZone localTimeZone]];
    NSDate* datenow = [NSDate date];
    timeSp = [formatter stringFromDate:datenow];
    NSLog(@"%@",datenow);
    return timeSp;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
