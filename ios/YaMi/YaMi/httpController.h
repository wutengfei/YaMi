//
//  httpController.h
//  YaMi
//
//  Created by BJCA on 2018/5/15.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface httpController : NSObject{
    NSMutableData* receiveData;
}
- (void)createRequest;
@end
