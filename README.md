# YoumeVideo_rtmp_live

1. 运行live-server-java-demo（运行步骤详细可以看文件里面的README.md文件）  可以调用相应的服务端推流接口

2. 再live-server-java-demo 这个demo中先调用 get_rtmp_publish_url 接口获取到推流地址

3. 调用  set_single_rtmp_param  设置单路rtmp服务端推流参数  目的是为了把video中的视频流数据推流到上面获取到的推流地址

4. 打开 YoumeVideoDemo_Android  demo来进行video视频通话或者直播

5. 调用 get_rtmp_play_url 获取到RTMP播放地址，可以用于播放video视频
