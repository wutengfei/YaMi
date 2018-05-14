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
    NSArray* infoArray;
    BaseLoopBanner* infoBanner ;
    UIScrollView* dateView;
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
    CGRect rect = CGRectMake(-0, 0, 0.25*sWidth, navHeight);
    NSMutableArray* tempButtonArray = [[NSMutableArray alloc]initWithCapacity:5];
    NSMutableArray* tempInfoArray = [[NSMutableArray alloc]initWithCapacity:5];
    dateView = [[UIScrollView alloc]initWithFrame:CGRectMake(0, 0, sWidth, navHeight)];
    for (int i = 0; i<5; i++) {
        //create weekdaybutton
        UIButton* newButton = [[UIButton alloc]initWithFrame:rect];
        [newButton setTitle:[NSString stringWithFormat:@"星期%i",i+1] forState:UIControlStateNormal];
        newButton.backgroundColor = [UIColor blueColor];
        [newButton addTarget:self action:@selector(clickDate:) forControlEvents:UIControlEventTouchUpInside];
        [tempButtonArray addObject:newButton];
        [dateView addSubview:newButton];
        rect.origin.x += sWidth*0.25;
        UILabel* newView = [[UILabel alloc]initWithFrame:CGRectMake(0, 0, sWidth, 530)];
        newView.backgroundColor = [UIColor whiteColor];
        //[newView addSubview:newButton];
        [newView setText:[NSString stringWithFormat:@"星期%i",i+1]];
        [tempInfoArray addObject:newView];
    }
    buttonArray = tempInfoArray;
    for (int i = 0; i<4; i++) {
        UIButton* newButton = [[UIButton alloc]initWithFrame:rect];
        rect.origin.x += sWidth*0.25;
        [newButton setTitle:[NSString stringWithFormat:@"星期%i",i+1] forState:UIControlStateNormal];
        newButton.backgroundColor = [UIColor blueColor];
        [newButton addTarget:self action:@selector(clickDate:) forControlEvents:UIControlEventTouchUpInside];
        [tempButtonArray addObject:newButton];
        [dateView addSubview:newButton];
    }
    [buttonArray arrayByAddingObjectsFromArray:tempInfoArray];
    infoArray = tempInfoArray;
    NSLog(@"%@",tempButtonArray[1]);
    infoBanner = [[BaseLoopBanner alloc]initWithFrame:CGRectMake(0, 0,sWidth, 530) viewArry:infoArray];
    [_infoView addSubview:infoBanner];
    //[buttonBanner getScrollView].scrollEnabled = false;
    dateView.contentSize = CGSizeMake(2.5*sWidth, navHeight);
    [_navView addSubview:dateView];
    //kvo add observer
    NSLog(@"currentIndex for infoBanner is%@",[infoBanner valueForKey:@"currentIndex"]);
    [infoBanner addObserver:self
                 forKeyPath:@"currentIndex"
                    options:NSKeyValueObservingOptionNew|NSKeyValueObservingOptionOld
                    context:@"context"];
}

- (void)dealloc{
    [infoBanner removeObserver:self forKeyPath:@"currentIndex"];
}
//kvo target
- (void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary<NSKeyValueChangeKey,id> *)change context:(void *)context{
    
    NSLog(@"currentIndex for infoBanner is%@",[infoBanner valueForKey:@"currentIndex"]);
    NSLog(@"context:%@,change:%@",context,change);
    if ([keyPath isEqualToString:@"currentIndex"]) {
        NSNumber* date = [change objectForKey:@"new"];
        float offset = (((int)date)%5*0.25+0.125)*sWidth;
        NSLog(@"%f",offset);
        dateView.contentOffset = CGPointMake(offset, 0);
    }
    
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
    NSString* str = [sender titleForState:UIControlStateNormal];
    int i = [[str substringFromIndex:str.length-1] intValue];
    NSLog(@"%i", i);
    [infoBanner setCurrentIndex:i];
}
@end
