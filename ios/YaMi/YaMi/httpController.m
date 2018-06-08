//
//  httpController.m
//  YaMi
//
//  Created by BJCA on 2018/5/15.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import "httpController.h"
@class Module;


@implementation httpController{}
- (void)getDataFromUrl:(NSString*)url{
    
    NSURL *nsgURL =[NSURL URLWithString:url];
    NSURLRequest* request = [NSURLRequest requestWithURL:nsgURL];
    NSURLSession* session = [NSURLSession sharedSession];
    NSURLSessionTask* sessionDataTask = [session dataTaskWithRequest:request
                                                   completionHandler:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {
                                                       NSLog(@"data get from server%@",data);
                                                       NSDictionary* dict = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableLeaves error:nil];
        NSLog(@"%@",dict);
    }];
    [sessionDataTask resume];
    NSLog(@"%@",sessionDataTask);
}
- (void)postDataToUrl:(NSString *)url withData:(NSData *)data{
    
}

@end
