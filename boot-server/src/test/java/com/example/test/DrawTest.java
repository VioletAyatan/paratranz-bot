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
        Surface surface = Surface.makeRaster(ImageInfo.makeN32Premul(1024, 1024));

        Canvas canvas = surface.getCanvas();

        Paint paint = new Paint();
        paint.setColor(Colors.RED);
        // 画圆
        canvas.drawCircle(250, 250, 200, paint);

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
        try (Typeface typeface = FontMgr.getDefault().matchFamilyStyle("Arial", FontStyle.NORMAL)) {
            // 创建字体
            int fontSize = 25;
            Font font = new Font(typeface, fontSize);
            Paint paint = new Paint().setColor(Colors.GREEN);

            //测量文本长度
            float textWidth = font.measureTextWidth(text);
            System.out.println("textWidth = " + textWidth + "px");
            // 创建画布
            Surface surface = Surface.makeRaster(ImageInfo.makeN32Premul((int) (textWidth + 5), fontSize + 5));
            Canvas canvas = surface.getCanvas();

            //x轴为0代表从图层最左上角开始绘制字体.
            // y轴表示纵轴绘制方向，这里设置为字体大小-5px.
            canvas.drawString(text, 0, fontSize-2, font, paint);
            //输出
            Image image = surface.makeImageSnapshot();
            writerToPng("Text.png", image);
        }
    }

    /**
     * 输出图像
     * @param outPutPath 输出路径
     * @param image 图片对象
     */
    private static void writerToPng(String outPutPath, Image image) {
        try {
            //图像编码为PNG
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
