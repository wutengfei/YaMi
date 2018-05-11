//
//  BaseLoopBanner.m
//  YaMi
//
//  Created by BJCA on 2018/5/11.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import "BaseLoopBanner.h"

@implementation BaseLoopBanner{
    int currentIndex;
    float width;
    float height;
    NSArray* viewArray;
    UIView* left;
    UIView* middle;
    UIView* right;
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/
- (instancetype)initWithFrame:(CGRect)frame viewArry:(NSArray* )array{
    if (self = [super initWithFrame:frame]) {
        currentIndex = 2;
        width = frame.size.width;
        height = frame.size.height;
        myScrollView = [[UIScrollView alloc]initWithFrame:frame];
        myScrollView.contentSize = CGSizeMake(3*width, height);
        myScrollView.contentOffset = CGPointMake(width, 0);
        myScrollView.delegate =self;
        viewArray = [[NSArray alloc]initWithArray:array];
        NSLog(@"%i",(int)viewArray.count);
        CGRect rect = CGRectMake(0, 0, width, height);
        left = [[UIView alloc]initWithFrame:rect];
        left.frame = rect;
        [left addSubview:viewArray[0]];
        rect.origin.x += width;
        middle = [[UIView alloc]initWithFrame:rect];
        middle.frame = rect;
        [middle addSubview:viewArray[1]];

        rect.origin.x += width;
        
        right = [[UIView alloc]initWithFrame:rect];
        right.frame = rect;
        [right addSubview:viewArray[2]];
        [myScrollView addSubview:left];
        [myScrollView addSubview:middle];
        [myScrollView addSubview:right];
        [self addSubview:myScrollView];
    }
    return self;
}


//scrollview delegate
-(void)scrollViewWillBeginDecelerating:(UIScrollView *)scrollView{
    //NSLog(@"did begin decelerating");
}
-(void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView{
    //NSLog(@"did end decelerating");
    //finish position
    scrollView.contentOffset = CGPointMake(width,0);
}
-(void)scrollViewDidScroll:(UIScrollView *)scrollView{
    //current position
    //make it endless
    if (scrollView.contentOffset.x < (int)(0.5*width)) {
        NSLog(@"did scroll left%@",NSStringFromCGPoint(scrollView.contentOffset));
        NSLog(@"current index is%i",currentIndex);
        currentIndex--;
        if (currentIndex==0) {
            
            currentIndex = (int)viewArray.count-1;
            left = [viewArray lastObject];
        }
        else{
            left = viewArray[currentIndex-2];
        }
        if (currentIndex==(int)viewArray.count) {
            right = viewArray[0];
        }
        else{
            right = viewArray[currentIndex];
        }
        middle = viewArray[currentIndex-1];
        scrollView.contentOffset = CGPointMake((int)(1.5*width), 0);
    }
    else if(scrollView.contentOffset.x > (int)(1.5*width)){
        //NSLog(@"did scroll right%@",NSStringFromCGPoint(scrollView.contentOffset));
        currentIndex++;
        NSLog(@"current index is%i",currentIndex);
        if (currentIndex==viewArray.count+1) {
            currentIndex = 1;
            [(UIView*)[left subviews][0] removeFromSuperview];
            [left addSubview:[viewArray lastObject]];
        }
        else{
            [(UIView*)[left subviews][0] removeFromSuperview];
            [left addSubview:viewArray[currentIndex-2]];
            NSLog(@"left");
        }
        if (currentIndex==(int)viewArray.count) {
            [(UIView*)[right subviews][0] removeFromSuperview];
            [right addSubview:viewArray[0]];
            NSLog(@"right");
        }
        else{
            [(UIView*)[right subviews][0] removeFromSuperview];
            [right addSubview:viewArray[currentIndex]];
            NSLog(@"right");
        }
        NSLog(@"%i",currentIndex);
        [(UIView*)[middle subviews][0] removeFromSuperview];
        [middle addSubview:viewArray[currentIndex-1]];
        NSLog(@"finish");
        scrollView.contentOffset = CGPointMake((int)(0.5*width), 0);
    }
}
-(void)scrollViewWillBeginDragging:(UIScrollView *)scrollView{
    //NSLog(@"did drag%@",NSStringFromCGPoint(scrollView.contentOffset));
}
-(void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate{
    //finish position
    //NSLog(@"finish drag,delerate =%i",decelerate);
    scrollView.contentOffset = CGPointMake(width,0);
    
}

@end
