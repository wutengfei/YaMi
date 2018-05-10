//
//  orderViewController.m
//  YaMi
//
//  Created by BJCA on 2018/5/9.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import "orderViewController.h"

@implementation orderViewController{
    NSString* restrant;
    NSArray* menu;
    __weak IBOutlet UILabel *restaurantName;
}
- (IBAction)doOrder:(UIButton *)sender {
    NSLog(@"%@",sender.restorationIdentifier);
}


/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/
-(void)viewDidLoad{
    [super viewDidLoad];
    [self.storyboard instantiateViewControllerWithIdentifier:@"orderViewController"];
    NSLog(@"%@", self.storyboard);
    restaurantName.text = @"rest";
    restaurantName.hidden = YES;
}

@end
