//
//  submitViewController.m
//  YaMi
//
//  Created by BJCA on 2018/5/14.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import "submitViewController.h"
#import "Module.h"
@interface submitViewController (){
    NSString* endTimeStr;
    __weak IBOutlet UILabel *endLabel;
    __weak IBOutlet UITextField *nameTextField;
    __weak IBOutlet UITextField *secretTextField;
    __weak IBOutlet UILabel *meal;
    __weak IBOutlet UILabel *add;
}

@end

@implementation submitViewController
- (IBAction)goBackToOrder:(UIBarButtonItem *)sender {
    //
    [self dismissViewControllerAnimated:NO completion:^(void){
        NSLog(@"%@",[self.presentingViewController.presentingViewController valueForKey:@"restrant"]);
        NSLog(@"goBackToOrder");
    }];
    
    
}
- (IBAction)submit:(UIButton *)sender {
    //make json
    NSString* name = nameTextField.text;
    NSString* secret = secretTextField.text;
    NSString* meal = [Module getMeal];
    NSString* add = [Module getAdd];
    NSMutableDictionary* tempDic = [[NSMutableDictionary alloc]init];
    [tempDic setValue:name forKey:@"name"];
    [tempDic setValue:secret forKey:@"secret"];
    [tempDic setValue:meal forKey:@"order"];
    [tempDic setValue:add forKey:@"add"];
    NSDictionary* dict = [NSDictionary dictionaryWithObject:tempDic forKey:@"message"];
    NSJSONSerialization* json = [NSJSONSerialization dataWithJSONObject:dict
                                                                options:NSJSONWritingPrettyPrinted
                                                                  error:nil];
    dict = nil;
    NSDictionary* dic = [NSJSONSerialization JSONObjectWithData:json options:NSJSONReadingMutableLeaves error:nil];
    NSLog(@"%@",dic);
    NSLog(@"%@",json);
    //post the json
    
    //go back to the index
    [self.presentingViewController.presentingViewController dismissViewControllerAnimated:NO completion:^(void){
        [Module setAdd:nil];
        [Module setMeal:nil];
    }];
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
    if ([Module getMeal]) {
        meal.text = [Module getMeal];
    }
    else{
        meal.text = @"没有选择";
    }
    if ([Module getAdd]) {
        add.text = [Module getAdd];
    }
    else{
        add.text = @"没有选择";
    }
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
    //NSLog(@"%@", str);
    [endLabel setText:str];
}

- (NSTimeInterval)getTimeToEnd{
    NSString* timeSp;
    NSDateFormatter* formatter = [[NSDateFormatter alloc]init];
    [formatter setDateStyle:NSDateFormatterMediumStyle];
    [formatter setTimeStyle:NSDateFormatterShortStyle];
    [formatter setDateFormat:@"YYYYMMdd"];
    //NSLog(@"%@",[NSTimeZone localTimeZone]);
    [formatter setTimeZone:[NSTimeZone localTimeZone]];
    NSDate* dateNow = [NSDate date];
    timeSp = [formatter stringFromDate:dateNow];
    [formatter setDateFormat:@"YYYYMMddHHmmss"];
    dateNow = [NSDate date];
    NSString* endTimeString = [NSString stringWithFormat:@"%@%@",timeSp,endTimeStr];
    NSDate* endTime = [formatter dateFromString:endTimeString];
    NSTimeInterval time = [endTime timeIntervalSinceDate:dateNow];
    //NSLog(@"%lld",(long long int)time);
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
