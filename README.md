# fingerprints
spring-boot 2.0 fingerprints demo

使用浏览器硬件信息实现移动设备唯一标识（WIFI 环境下 相同型号设备有重复率）

|名称|方法|
|-|-|
|屏幕宽度|window.screen.width|
|屏幕高度|window.screen.height|
|设备像素比|window.devicePixelRatio|
|颜色深度|window.screen.colorDepth|

```
    var canvas = document.createElement("canvas");
    var gl = canvas.getContext("experimental-webgl");
    var debugInfo = gl.getExtension("WEBGL_debug_renderer_info");
```
|名称|方法|
|-|-|
|OpenGL 版本|gl.getParameter(gl.VERSION)|
|显卡渲染器|gl.getParameter(debugInfo.UNMASKED_RENDERER_WEBGL)|
|厂商|gl.getParameter(gl.VENDOR)|
|OpenGL shading language版本|gl.getParameter(gl.SHADING_LANGUAGE_VERSION)|
|最大字体限制|gl.getParameter(gl.MAX_TEXTURE_SIZE)|
|显卡供应商|gl.getParameter(debugInfo.UNMASKED_VENDOR_WEBGL)|
|用户代理|navigator.userAgent|

上传测试地址

http://127.0.0.1:8080/?k1=v1&k2=v2

获取数据地址

http://127.0.0.1:8080/test.html

