//
//  previewViewController.h
//  YaMi
//
//  Created by BJCA on 2018/5/10.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BaseLoopBanner.h"

@interface previewViewController : UIViewController<UIScrollViewDelegate>
@property (weak, nonatomic) IBOutlet UIView *navView;
@property (weak, nonatomic) IBOutlet UIView *infoView;
@end
