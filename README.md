# CloudReader

[![Apache License 2.0][1]][2]
[![fir.im][3]][4] 
[![Release Version][5]][6]
[![API][7]][8]
[![PRs Welcome][9]][10]

## Introduce
一款使用豆瓣api，Gank.Io和网易新闻开发的阅读类Android客户端开源项目。项目采取的是Retrofit + RxJava + ButterKnife架构开发，ui 参考了bilibili和网易云音乐。

An Android client using Gank.Io， douban API and netease news. The reading class open source project is based on Retrofit + RxJava + ButterKnife.

## Screenshots


<img width="30%"  src="https://github.com/philipli1995/SmartZone/blob/master/image/page_book_list.jpg"></img>
<img width="30%"  src="https://github.com/philipli1995/SmartZone/blob/master/image/page_download.jpg"></img>
<img width="30%"  src="https://github.com/philipli1995/SmartZone/blob/master/image/page_news.jpg"></img>
<img width="30%"  src="https://github.com/philipli1995/SmartZone/blob/master/image/page_news_list.jpg"></img>
<img width="30%"  src="https://github.com/philipli1995/SmartZone/blob/master/image/page_nav_bar.jpg"></img>
<img width="30%"  src="https://github.com/philipli1995/SmartZone/blob/master/image/page_meizi.jpg"></img>

## Issues 宝贵意见
如果有任何问题，请到github的[issue处][11]写上你不明白的地方,我会及时给予帮助。

If you have any questions, please write to [the issue][11] of making you don't understand of place, also can contact me through here, I will help them in time.

## Features 特性
* 1、基本遵循Google Material Design设计风格。                                    
* 2、内容包含gank干货集中营,豆瓣书籍以及网易新闻。                                    
* 3、RxJava+ButterKnfe的项目应用。                                    
* 4、``NavigationView``搭配``DrawerLayout``的具体使用。                                    
* 5、``ToolBar``的全方面使用。                                    
* 6、``Glide``加载监听，提供及获取缓存，解析Html文本中的图片，高斯模糊。                                    
* 7、水波纹点击效果使用与适配。                                    
* 8、``RecyclerView``下拉刷新，上拉加载。                                              
* 9、``Fragment``懒加载模式。                                    
* 10、``SwipeRefreshLayout``结合``RecyclerView``下拉刷新上拉加载。                                    
         




### Download
　[下载页面](https://fir.im/SmartZone)
　

### Version


#### V1.1.0（1-2）

 - [新增]1.书籍、新闻详情页面增加自定义元素共享切换动画
 - [新增]2.二维码扫码下载页面
 - [修复]3.新闻详情页面的图片可以显示了
 - [优化]4.为部分glide已加载的图片提供缓存

#### V1.1.2（1-5）

  - [新增]1.在主页面双击退出会退出程序
  - [新增]2.新闻界面增加分享功能
  - [修复]3.修复了下拉栏加载不出来进入空白页的异常
  - [优化]4.部分代码优化

　　



## Thanks to the open source library

- [RxJava][12]
- [RxAndroid][13]
- [RxLifecycle][14]
- [OkHttp][15]
- [Retrofit][16]
- [ButterKnife][17]
- [Glide][18]
- [FlycoTabLayout][19]
- [glide-transformations][20]
- [PhotoView][21]
 

## Thanks to the reference project

 - [bilibili-android-client][22]
 - [CloudReader][23]
 - [AndroidFire][24]
 - [LiveCircle][25]
 - [LookLook][26]



## Thanks to the free icon library
 - [Material Design][27]
 - [IconFinder][28]


 
## Statement
> 注意：此开源项目仅做学习交流使用，如用到实际项目还需多考虑其他因素如并发等，请多多斟酌。如果你觉得不错，对你有帮助，欢迎点个fork，star，follow，也可以帮忙分享给你更多的朋友，这是对我最大的帮助与支持。

## About me
 - **QQ：** 1296346687
 - **Blog：**[http://philipli-blog.com/](http://philipli-blog.com/)
 - **Email：** philipli1995@gmail.com // 1296346687@qq.com

## License
```
Copyright (C) 2018 Philip Li

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[1]:https://img.shields.io/:license-apache-blue.svg
[2]:https://www.apache.org/licenses/LICENSE-2.0.html

[3]:https://img.shields.io/badge/download-fir.im-blue.svg
[4]:https://fir.im/SmartZone

[5]:https://img.shields.io/badge/release-1.1.0-red.svg
[6]:https://github.com/philipli1995/SmartZone/releases

[7]:https://img.shields.io/badge/API-21%2B-red.svg?style=flat
[8]:https://android-arsenal.com/api?level=21


[9]:https://img.shields.io/badge/PRs-welcome-brightgreen.svg
[10]:https://github.com/philipli1995/SmartZone/pulls

[11]:https://github.com/philipli1995/SmartZone/issues

[12]:https://github.com/ReactiveX/RxJava
[13]:https://github.com/ReactiveX/RxAndroid
[14]:https://github.com/trello/RxLifecycle
[15]:https://github.com/square/okhttp
[16]:https://github.com/square/retrofit
[17]:https://github.com/JakeWharton/butterknife
[18]:https://github.com/bumptech/glide
[19]:https://github.com/H07000223/FlycoTabLayout
[20]:https://github.com/wasabeef/glide-transformations
[21]:https://github.com/chrisbanes/PhotoView

[22]:https://github.com/HotBitmapGG/bilibili-android-client
[23]:https://github.com/youlookwhat/CloudReader
[24]:https://github.com/jaydenxiao2016/AndroidFire
[25]:https://github.com/LRH1993/LiveCircle
[26]:https://github.com/xinghongfei/LookLook


[27]:https://material.io/icons/
[28]:https://www.iconfinder.com/

