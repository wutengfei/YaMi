//
//  httpController.m
//  YaMi
//
//  Created by BJCA on 2018/5/15.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import "httpController.h"
@class Module;

@implementation httpController{
    
}
#pragma mark NSURLConnectionDelegate
- (void)connection:(NSURLConnection *)connection didReceiveResponse:(NSURLResponse *)response{
    [receiveData setLength:0];
}

- (void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data{
    [receiveData appendData:data];
}

- (void)connection:(NSURLConnection *)connection didFailWithError:(NSError *)error{
    [[NSNotificationCenter defaultCenter] postNotificationName:@"FAILURE" object:nil userInfo:nil];
    if(receiveData!=nil)
    {
        receiveData=nil;
    }
}

- (void)connectionDidFinishLoading:(NSURLConnection *)connection{
    NSString* retString = [NSString stringWithUTF8String:[receiveData bytes]];
    NSLog(@"返回的数据是%@",retString);
}
- (void)createRequest{
    
    NSURL *nsgURL =[NSURL URLWithString:@"https://www.baidu.com"];
    NSURLRequest* request = [NSURLRequest requestWithURL:nsgURL
                                             cachePolicy:NSURLRequestUseProtocolCachePolicy
                                         timeoutInterval:60.0];
    NSURLConnection* theConnection = [[NSURLConnection alloc]initWithRequest:request delegate:self];
    if (theConnection) {
        NSLog(@"%@",theConnection);
        receiveData = [[NSMutableData alloc]init];
    }
}
@end
