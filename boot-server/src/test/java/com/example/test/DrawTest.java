package com.example.test;

import io.github.humbleui.skija.*;
import org.junit.jupiter.api.Test;
import org.paratranz.bot.Colors;

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
    public void drawCircle() {
        Surface surface = Surface.makeRaster(ImageInfo.makeN32Premul(100, 100));

        Canvas canvas = surface.getCanvas();

        Paint paint = new Paint();
        paint.setColor(Colors.RED);
        // 画圆
        canvas.drawCircle(50, 50, 50, paint);

        //输出
        Image image = surface.makeImageSnapshot();

        writerToPng("output.png", image);
    }

    @Test
    public void drawText() {
        final String text = "[From.GetName]... From orbit, it appears so small." + "\n\n" +
                "A world does not belong to its people; it is the people who belong to their world. " +
                "In returning here, we take another step towards reclaiming our past." +
                "\n\n" +
                "The trauma we experienced at the hands of our former masters can never be undone. " +
                "True healing will take generations. But today is a good day.";
        Surface surface = Surface.makeRaster(ImageInfo.makeN32Premul(3875, 25));
        Canvas canvas = surface.getCanvas();

        try (Typeface menlo = FontMgr.getDefault().matchFamilyStyle("Arial", FontStyle.NORMAL)) {
            // 创建字体
            Font font = new Font(menlo, 25);
            Paint paint = new Paint().setColor(Colors.RED);

            float textWidth = font.measureTextWidth(text);
            System.out.println("文本长度 = " + textWidth + "px");

            //x轴为0代表从图层最左上角开始绘制字体.y轴标识纵轴绘制的方向.
            canvas.drawString(text, 0, 25 - 5, font, paint);
        }

        //输出
        Image image = surface.makeImageSnapshot();
        writerToPng("Text.png", image);
    }

    private static void writerToPng(String outPutPath, Image image) {
        try {
            Data encode = EncoderPNG.encode(image);
            ByteBuffer byteBuffer = encode.toByteBuffer();
            SeekableByteChannel byteChannel = Files.newByteChannel(Path.of(outPutPath),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE
            );
            byteChannel.write(byteBuffer);
            byteChannel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
