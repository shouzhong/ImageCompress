# Luban
## 说明
图片压缩
## 使用
### 依赖
```
implementation 'com.shouzhong:ImageCompress:1.0.10'
```
### 代码
```
/**
 * 图片压缩，请在子线程运行
 *
 * @param filePath 原始图片
 * @param targetDir 压缩后图片保存的目录，不传为当前app目录的files/image
 * @param leastCompressSize 不压缩的阈值，单位为K
 * @param focusAlpha 是否保留透明通道
 * @return 压缩后的图片路径
 */
public static String compress(String filePath, String targetDir, int leastCompressSize, boolean focusAlpha)
```
