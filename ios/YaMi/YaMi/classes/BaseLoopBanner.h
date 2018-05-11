//
//  BaseLoopBanner.h
//  YaMi
//
//  Created by BJCA on 2018/5/11.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface BaseLoopBanner : UIView<UIScrollViewDelegate>{
    
    UIScrollView* myScrollView;
}
- (instancetype)initWithFrame:(CGRect)frame viewArry:(NSArray* )array;
@end
