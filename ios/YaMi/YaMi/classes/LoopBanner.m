//
//  LoopBanner.m
//  YaMi
//
//  Created by BJCA on 2018/5/8.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import "LoopBanner.h"

@implementation LoopBanner{
float x;
float y;
}
/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

-(UIColor*)getMiddle{
    return middleText.backgroundColor;
}

-(LoopBanner *)initWithFrame:(CGRect)frame{
    if (self = [super initWithFrame:frame]) {
        NSLog(@"init Loop Banner");
        x = CGRectGetWidth(frame);
        y = CGRectGetHeight(frame);
        CGRect rect = CGRectMake(0, 0, 0.8*x, y);
        CGRect textRect = CGRectMake(0, 0, 0.7*x, 0.7*y);
        myScrollView = [[UIScrollView alloc]initWithFrame:CGRectMake(0, 0, x, y)];
        left = [[UIView alloc]initWithFrame:rect];
        leftText = [[UIView alloc]initWithFrame:textRect];
        leftText.center = left.center;
        leftText.backgroundColor = [UIColor redColor];
        [left addSubview:leftText];
        NSLog(@"leftCenter is%@,leftTextCenter is%@",NSStringFromCGPoint(left.center),NSStringFromCGPoint(leftText.center));
        rect.origin.x = 0.8*x;
        middle = [[UIView alloc]initWithFrame:rect];
        middleText = [[UIView alloc]initWithFrame:rect];
        //why left center;
        middleText.center = left.center;
        middleText.backgroundColor = [UIColor blueColor];
        [middle addSubview:middleText];
        NSLog(@"middleCenter is%@,middleTextCenter is%@",NSStringFromCGPoint(middle.center),NSStringFromCGPoint(middleText.center));
        NSLog(@"leftCenter is%@,leftTextCenter is%@",NSStringFromCGPoint(left.center),NSStringFromCGPoint(leftText.center));
        rect.origin.x = 1.6*x;
        right = [[UIView alloc]initWithFrame:rect];
        rightText = [[UIView alloc]initWithFrame:textRect];
        rightText.center = left.center;
        rightText.backgroundColor = [UIColor greenColor];
        [right addSubview:rightText];
        NSLog(@"rightCenter is%@,rightTextCenter is%@",NSStringFromCGPoint(right.center),NSStringFromCGPoint(rightText.center));
        NSLog(@"leftCenter is%@,leftTextCenter is%@",NSStringFromCGPoint(left.center),NSStringFromCGPoint(leftText.center));
        [myScrollView addSubview:left];
        [myScrollView addSubview:middle];
        [myScrollView addSubview:right];
        myScrollView.contentSize = CGSizeMake(2.4*x, y);
        myScrollView.contentOffset = CGPointMake(0.7*x, 0);
        myScrollView.delegate = self;
        myScrollView.decelerationRate = 0;
        [self addSubview:myScrollView];
        for (int i = 0; i<items.count; i++) {
            //[self addSubview:items[i]];
            
        }
    }
    return self;
}

//scrollview delegate
-(void)scrollViewWillBeginDecelerating:(UIScrollView *)scrollView{
    NSLog(@"did begin decelerating");
}
-(void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView{
    NSLog(@"did end decelerating");
    //finish position
    scrollView.contentOffset = CGPointMake(0.7*x,0);
}
-(void)scrollViewDidScroll:(UIScrollView *)scrollView{
    //current position
    //make it endless
    if (scrollView.contentOffset.x < (int)(0.35*x)) {
        NSLog(@"did scroll left%@",NSStringFromCGPoint(scrollView.contentOffset));
        UIColor* temp = rightText.backgroundColor;
        rightText.backgroundColor = middleText.backgroundColor;
        middleText.backgroundColor = leftText.backgroundColor;
        leftText.backgroundColor = temp;
        myScrollView.contentOffset = CGPointMake((int)(1.05*x), 0);
    }
    else if(scrollView.contentOffset.x > (int)(1.05*x)){
        NSLog(@"did scroll right%@",NSStringFromCGPoint(scrollView.contentOffset));
        UIColor* temp = leftText.backgroundColor;
        leftText.backgroundColor = middleText.backgroundColor;
        middleText.backgroundColor = rightText.backgroundColor;
        rightText.backgroundColor = temp;
        scrollView.contentOffset = CGPointMake((int)(0.35*x), 0);
    }
    //change size
    float distance = fabs(scrollView.contentOffset.x-0.7*x)/x;
    leftText.frame = CGRectMake(leftText.frame.origin.x, leftText.frame.origin.y, (0.7+distance/7)*x, (0.7+3*distance/7)*y);
    leftText.center = left.center;
    
    middleText.frame = CGRectMake(middleText.frame.origin.x, middleText.frame.origin.y, (0.8-distance/7)*x, (1.0-3*distance/7)*y);
    middleText.center = left.center;
    
    rightText.frame = CGRectMake(rightText.frame.origin.x, rightText.frame.origin.y, (0.7+distance/7)*x, (0.7+3*distance/7)*y);
    rightText.center = left.center;

    //NSLog(@"did scroll%@",NSStringFromCGPoint(scrollView.contentOffset));
}
-(void)scrollViewWillBeginDragging:(UIScrollView *)scrollView{
    NSLog(@"did drag%@",NSStringFromCGPoint(scrollView.contentOffset));
}
-(void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate{
    //finish position
    NSLog(@"finish drag,delerate =%i",decelerate);
    scrollView.contentOffset = CGPointMake(0.7*x,0);
    
}
@end
