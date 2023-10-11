package com.example.test;

import io.github.humbleui.skija.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class DrawTest {
    /**
     * 绘图测试
     */
    @Test
    public void drawTest() {
        Surface surface = Surface.makeRaster(ImageInfo.makeN32Premul(100, 100));

        Canvas canvas = surface.getCanvas();

        Paint paint = new Paint();
        paint.setColor(0xFFFF0000);
        // 画圆
        canvas.drawCircle(50, 50, 50, paint);

        //输出
        Image image = surface.makeImageSnapshot();
        Data pngData = EncoderPNG.encode(image);
        ByteBuffer byteBuffer = pngData.toByteBuffer();

        try {
            SeekableByteChannel byteChannel = Files.newByteChannel(Path.of("output.png"),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE
            );
            byteChannel.write(byteBuffer);
            byteChannel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
