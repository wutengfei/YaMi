//
//  previewViewController.m
//  YaMi
//
//  Created by BJCA on 2018/5/10.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import "previewViewController.h"

@interface previewViewController (){
    float sWidth;
    float sHeight;
    NSArray* buttonArray;
    
}

@end

@implementation previewViewController
- (IBAction)goBackToOrder:(UIBarButtonItem *)sender {
    [self dismissViewControllerAnimated:NO completion:nil];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    sWidth = self.view.frame.size.width;
    sHeight = self.view.frame.size.height;
    float navHeight = _navView.frame.size.height;
    CGRect rect = CGRectMake(0, 0, 0.2*sWidth, navHeight);
    NSMutableArray* tempButtonArray = [[NSMutableArray alloc]initWithCapacity:5];
    NSMutableArray* tempInfoArray = [[NSMutableArray alloc]initWithCapacity:5];
    for (int i = 0; i<5; i++) {
        //create weekdaybutton
        UIButton* newButton = [[UIButton alloc]initWithFrame:rect];
        [newButton setTitle:[NSString stringWithFormat:@"星期%i",i+1] forState:UIControlStateNormal];
        newButton.backgroundColor = [UIColor blueColor];
        [newButton addTarget:self action:@selector(clickDate:) forControlEvents:UIControlEventTouchUpInside];
        [tempButtonArray addObject:newButton];
        [_navView addSubview:newButton];
        rect.origin.x += sWidth*0.2;
        UILabel* newView = [[UILabel alloc]initWithFrame:CGRectMake(0, 0, sWidth, 500)];
        newView.backgroundColor = [UIColor whiteColor];
        //[newView addSubview:newButton];
        [newView setText:[NSString stringWithFormat:@"%i",i]];
        [tempInfoArray addObject:newView];
    }
    buttonArray = tempButtonArray;
    NSLog(@"%@",tempButtonArray[1]);
    BaseLoopBanner* loopBanner = [[BaseLoopBanner alloc]initWithFrame:CGRectMake(0, 0, sWidth, 530) viewArry:tempInfoArray];
    [_infoView addSubview:loopBanner];
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
- (void)clickDate:(UIButton*)sender{
    NSLog(@"%@", [sender titleForState:UIControlStateNormal]);
}
@end
