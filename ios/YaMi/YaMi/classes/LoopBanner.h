//
//  LoopBanner.h
//  YaMi
//
//  Created by BJCA on 2018/5/8.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface LoopBanner : UIView<UIScrollViewDelegate>{
    NSArray* items;
    UIView* left;
    UIView* leftText;
    UIView* middle;
    UIView* middleText;
    UIView* right;
    UIView* rightText;
    UIScrollView* myScrollView;

}

-(LoopBanner*)initWithFrame:(CGRect)frame;
-(UIColor*)getMiddle;
@end
