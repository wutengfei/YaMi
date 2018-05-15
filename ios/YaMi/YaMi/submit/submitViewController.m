//
//  submitViewController.m
//  YaMi
//
//  Created by BJCA on 2018/5/14.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import "submitViewController.h"

@interface submitViewController (){
    NSString* endTimeStr;
    __weak IBOutlet UILabel *endLabel;
}

@end

@implementation submitViewController
- (IBAction)goBackToOrder:(UIBarButtonItem *)sender {
    //
    [self dismissViewControllerAnimated:NO completion:nil];
    
    
}

- (void)viewDidLoad {
    [super viewDidLoad];
    endTimeStr = @"180000";
    NSDateFormatter* dateFormatter = [[NSDateFormatter alloc]init];
    [dateFormatter setTimeZone:[NSTimeZone localTimeZone]];
    [dateFormatter setDateFormat:@"HHmmss"];
    NSDate* endTime = [dateFormatter dateFromString:endTimeStr];
    NSLog(@"%@",endTime);
    [self getTimeToEnd];
    // Do any additional setup after loading the view.
    //start Timmer
    [NSTimer scheduledTimerWithTimeInterval:1 target:self selector:@selector(timerAction) userInfo:nil repeats:YES];
}

- (void) timerAction{
    long time = (long long int)[self getTimeToEnd];
    int second = time%60;
    int hour = (int)time/3600;
    int minute = (int)(time - hour*3600)/60;
    NSString* str;
    if (time>0) {
        str = [NSString stringWithFormat:@"%i小时，%i分，%i秒",hour,minute,second];
    }
    else{
        str = @"您已经过了今天点餐时间哈哈哈哈哈哈";
    }
    NSLog(@"%@", str);
    [endLabel setText:str];
}

- (NSTimeInterval)getTimeToEnd{
    NSString* timeSp;
    NSDateFormatter* formatter = [[NSDateFormatter alloc]init];
    [formatter setDateStyle:NSDateFormatterMediumStyle];
    [formatter setTimeStyle:NSDateFormatterShortStyle];
    [formatter setDateFormat:@"YYYYMMdd"];
    NSLog(@"%@",[NSTimeZone localTimeZone]);
    NSTimeZone* timeZone = [NSTimeZone timeZoneWithName:@"Asia/Shanghai"];
    [formatter setTimeZone:[NSTimeZone localTimeZone]];
    NSDate* dateNow = [NSDate date];
    timeSp = [formatter stringFromDate:dateNow];
    [formatter setDateFormat:@"YYYYMMddHHmmss"];
    dateNow = [NSDate date];
    NSString* endTimeString = [NSString stringWithFormat:@"%@%@",timeSp,endTimeStr];
    NSDate* endTime = [formatter dateFromString:endTimeString];
    NSTimeInterval time = [endTime timeIntervalSinceDate:dateNow];
    NSLog(@"%lld",(long long int)time);
    return time;
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
