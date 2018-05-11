//
//  orderViewController.m
//  YaMi
//
//  Created by BJCA on 2018/5/9.
//  Copyright © 2018年 BJCA. All rights reserved.
//

#import "orderViewController.h"

@implementation orderViewController{
    float screenHeight;
    float screenWidth;
    NSString* restrant;
    NSArray* menu;
    UIButton* choosedMeal;
    UIButton* choosedAdd;
    __weak IBOutlet UILabel *restaurantName;
    __weak IBOutlet UIView *mealView;
    __weak IBOutlet UIView *addView;
    __weak IBOutlet UIButton *orderButton;
    __weak IBOutlet UIButton *addButton;
    __weak IBOutlet UIButton *randomButton;
}
- (IBAction)submit:(UIButton *)sender {
}
- (IBAction)goToPreview:(UIBarButtonItem *)sender {
    UIStoryboard* sb = [UIStoryboard storyboardWithName:@"Preview" bundle:nil];
    UIViewController* preview = [sb instantiateViewControllerWithIdentifier:@"previewViewController"];
    [self presentViewController:preview animated:NO completion:nil];
}

- (IBAction)backToIndex:(UIBarButtonItem *)sender {
    [self dismissViewControllerAnimated:NO completion:nil];
}
- (IBAction)doOrder:(UIButton *)sender {
    int meal = 0;
    int add = 0;
    if([sender.restorationIdentifier isEqual: @"random"]){
        meal = rand()%5+1;
        add = rand()%4+1;
        NSLog(@"扭蛋一下,吃%i，加%i",meal,add);
    }
    else if([sender.restorationIdentifier isEqual: @"set1"]){
        meal = 1;
    }
    else if([sender.restorationIdentifier isEqual: @"set2"]){
        meal = 2;
    }
    else if([sender.restorationIdentifier isEqual: @"set3"]){
        meal = 3;
    }
    else if([sender.restorationIdentifier isEqual: @"set4"]){
        meal = 4;
    }
    else if([sender.restorationIdentifier isEqual: @"set5"]){
        meal = 5;
    }
    else if([sender.restorationIdentifier isEqual: @"add1"]){
        add = 1;
    }
    else if([sender.restorationIdentifier isEqual: @"add2"]){
        add = 2;
    }
    else if([sender.restorationIdentifier isEqual: @"add3"]){
        add = 3;
    }
    else if([sender.restorationIdentifier isEqual: @"add4"]){
        add = 4;
    }
    else{
        UIAlertController* alert = [UIAlertController alertControllerWithTitle:@"WRONG?"
                                                                       message:@"what did I trapped?"
                                                                preferredStyle:UIAlertControllerStyleAlert];
        [self presentViewController:alert animated:NO completion:nil];
    }
    switch (meal) {
        case 0:
            break;
        case 1:
            meal = 1;
            NSLog(@"%i",meal);
            [self chooseMeal:meal];
            break;
        case 2:
            meal = 2;
            NSLog(@"%i",meal);
            [self chooseMeal:meal];
            break;
            
        case 3:
            meal = 3;
            NSLog(@"%i",meal);
            [self chooseMeal:meal];
            break;
            
        case 4:
            meal = 4;
            NSLog(@"%i",meal);
            [self chooseMeal:meal];
            break;
            
        case 5:
            meal = 5;
            NSLog(@"%i",meal);
            [self chooseMeal:meal];
            break;
            
        default:
            break;
    }
    switch (add) {
        case 0:
            break;
        case 1:
            add = 1;
            NSLog(@"%i",add);
            [self chooseAdd:add];
            break;
            
        case 2:
            add = 2;
            NSLog(@"%i",add);
            [self chooseAdd:add];
            break;
            
        case 3:
            add = 3;
            NSLog(@"%i",add);
            [self chooseAdd:add];
            break;
            
        case 4:
            add = 4;
            NSLog(@"%i",add);
            [self chooseAdd:add];
            break;

            
        default:
            break;
    }
    NSLog(@"%@",sender.restorationIdentifier);
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/
-(void)viewDidLoad{
    [super viewDidLoad];
    screenHeight = self.view.frame.size.height;
    screenWidth = self.view.frame.size.width;
    NSLog(@"screenHeight is%f,screehWidth is%f",screenHeight,screenWidth);
    [self.storyboard instantiateViewControllerWithIdentifier:@"orderViewController"];
    NSLog(@"%@", self.storyboard);
    restaurantName.text = @"rest";
    addView.frame = mealView.frame;
    addView.hidden = YES;
    orderButton.selected = YES;
    [addButton addTarget:self action:@selector(showAdd) forControlEvents: UIControlEventTouchUpInside];
    [orderButton addTarget:self action:@selector(showMeal) forControlEvents: UIControlEventTouchUpInside];
}
- (void)showAdd{
    addView.hidden = NO;
    mealView.hidden = YES;
    orderButton.selected = NO;
    addButton.selected = YES;
}
- (void)showMeal{
    addView.hidden = YES;
    mealView.hidden = NO;
    orderButton.selected = YES;
    addButton.selected = NO;
}
- (void)chooseMeal:(int)meal{
    NSLog(@"chooseMeal%d",meal);
    NSString *str = [NSString stringWithFormat:@"套餐%i",meal];
    if (!choosedMeal) {
        NSLog(@"create meal button");
        randomButton.hidden = YES;
        //already choosed,change this time
        CGRect rect = CGRectMake(100, 600, 90, 165);
        choosedMeal = [[UIButton alloc]initWithFrame:rect];
        choosedMeal.backgroundColor = [UIColor redColor];
        [choosedMeal addTarget:self action:@selector(dragButton:forEvent:) forControlEvents:UIControlEventTouchDragInside];
        [choosedMeal addTarget:self action:@selector(finishDragButton:forEvent:) forControlEvents:UIControlEventTouchUpInside];
        [self.view addSubview:choosedMeal];
    }
    [choosedMeal setTitle:str forState:UIControlStateNormal];
    return;
}
- (void)chooseAdd:(int)add{
    NSLog(@"chooseAdd%d",add);
    NSString *str = [NSString stringWithFormat:@"加料%i",add];
    if (!choosedAdd) {
        NSLog(@"create add button");
        //already choosed,change this time
        randomButton.hidden = YES;
        CGRect rect = CGRectMake(200, 600, 90, 165);
        choosedAdd = [[UIButton alloc]initWithFrame:rect];
        choosedAdd.backgroundColor = [UIColor redColor];
        [choosedAdd addTarget:self action:@selector(dragButton:forEvent:) forControlEvents:UIControlEventTouchDragInside];
        [choosedAdd addTarget:self action:@selector(finishDragButton:forEvent:) forControlEvents:UIControlEventTouchUpInside];
        [self.view addSubview:choosedAdd];
    }
    [choosedAdd setTitle:str forState:UIControlStateNormal];
    return;
}

- (void)finishDragButton:(UIButton *)sender forEvent:(UIEvent *)event{
    CGPoint touchPoint = CGPointMake([[[event allTouches]anyObject]locationInView:self.view].x,
                                     [[[event allTouches]anyObject]locationInView:self.view].y);
    if (touchPoint.y<screenHeight/3) {
        //delete button
        [sender removeFromSuperview];
        if (sender==choosedMeal) {
            choosedMeal = nil;
            if (!choosedAdd) {
                randomButton.hidden = NO;
            }
        }
        else if (sender==choosedAdd){
            choosedAdd = nil;
            if (!choosedMeal) {
                randomButton.hidden = NO;
            }
        }
    }
    else if ((600<touchPoint.y&&touchPoint.y<680)&&touchPoint.x){
        if (sender==choosedMeal) {
            sender.center = CGPointMake(145, 600);
        }
        else if (sender==choosedAdd){
            sender.center = CGPointMake(245, 600);
        }
        else{
            NSLog(@"@wrong%@",sender);
        }
    }
    else{
        //put it back
        if (sender==choosedMeal) {
            sender.center = CGPointMake(145, 680);
        }
        else if (sender==choosedAdd){
            sender.center = CGPointMake(245, 680);
        }
        else{
            NSLog(@"@wrong%@",sender);
        }
    }
}

- (void)dragButton:(UIButton *)sender forEvent:(UIEvent *)event {
    CGPoint touchPoint = CGPointMake([[[event allTouches]anyObject]locationInView:self.view].x,
                                       [[[event allTouches]anyObject]locationInView:self.view].y);
    sender.center = touchPoint;
    if (touchPoint.y<screenHeight/3) {
        //delete image
        sender.backgroundColor = [UIColor greenColor];
    }
    else{
        sender.backgroundColor = [UIColor redColor];
    }
}

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event{
    UITouch* touch = [touches anyObject];
    choosedMeal.center = CGPointMake(145, 680);
    choosedAdd.center = CGPointMake(245, 680);
    //NSLog(@"%@",NSStringFromCGPoint([touch locationInView:[touch view]]));
}
@end
