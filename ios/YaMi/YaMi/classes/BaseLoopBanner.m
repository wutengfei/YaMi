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

- (UIScrollView*)getScrollView{
    return myScrollView;
}

- (void)setCurrentIndex:(int)index{
    self->currentIndex = index;
    [(UIView*)[left subviews][0] removeFromSuperview];
    [(UIView*)[right subviews][0] removeFromSuperview];
    [(UIView*)[middle subviews][0] removeFromSuperview];
    if (currentIndex <=1) {
        currentIndex = 1;
        [left addSubview:[viewArray lastObject]];
        [middle addSubview:viewArray[currentIndex-1]];
        [right addSubview:viewArray[currentIndex]];
    }
    else if(currentIndex >= viewArray.count){
        currentIndex = (int)viewArray.count;
        [left addSubview:viewArray[currentIndex-2]];
        [middle addSubview:viewArray[currentIndex-1]];
        [right addSubview:viewArray[0]];
    }
    else{
        [left addSubview:viewArray[currentIndex-2]];
        [middle addSubview:viewArray[currentIndex-1]];
        [right addSubview:viewArray[currentIndex]];
    }
}


//scrollview delegate
-(void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView{
    //NSLog(@"did end decelerating");
    //finish position
    scrollView.contentOffset = CGPointMake(width,0);
}
-(void)scrollViewDidScroll:(UIScrollView *)scrollView{
    //current position
    //make it endless
    if (scrollView.contentOffset.x < (int)(0.5*width)) {
        if (currentIndex==1) {
            [self setCurrentIndex:(int)viewArray.count];
        }
        else{
            [self setCurrentIndex:currentIndex-1];
        }
        scrollView.contentOffset = CGPointMake((int)(1.5*width), 0);
    }
    else if(scrollView.contentOffset.x > (int)(1.5*width)){
        if (currentIndex==viewArray.count) {
            [self setCurrentIndex:1];
        }
        else{
            [self setCurrentIndex:currentIndex+1];
        }
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
