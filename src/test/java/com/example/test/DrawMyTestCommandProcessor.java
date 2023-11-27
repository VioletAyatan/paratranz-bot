package com.example.test;

import io.github.humbleui.skija.*;
import io.github.humbleui.skija.paragraph.*;
import org.junit.jupiter.api.Test;
import org.paratranz.bot.art.Colors;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class DrawMyTestCommandProcessor {
    /**
     * 颜料
     */
    private final Paint paint = new Paint();

    /**
     * 绘图测试
     */
    @Test
    public void drawCircle() {
        Surface surface = Surface.makeRaster(ImageInfo.makeN32Premul(1024, 1024));

        Canvas canvas = surface.getCanvas();

        paint.setColor(Colors.RED);
        // 画圆
        canvas.drawCircle(250, 250, 200, paint);

        //输出
        Image image = surface.makeImageSnapshot();

        writerToPng("output.png", image);
    }

    /**
     * 绘制文字
     */
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
            paint.setColor(Colors.BLUE);

            //测量文本长度
            float textWidth = font.measureTextWidth(text);
            System.out.println("textWidth = " + textWidth + "px");
            // 创建画布
            Surface surface = Surface.makeRaster(ImageInfo.makeN32Premul((int) (textWidth + 5), fontSize + 5));
            Canvas canvas = surface.getCanvas();

            //x轴为0代表从图层最左上角开始绘制字体.
            // y轴表示纵轴绘制方向，这里设置为字体大小-5px.
            canvas.drawString(text, 0, fontSize - 2, font, paint);
            //输出
            Image image = surface.makeImageSnapshot();
            writerToPng("Text.png", image);
        }
    }

    /**
     * 分段文字
     */
    @Test
    public void drawTextParagraph() {
        // 创建画布
        Surface surface = Surface.makeRaster(ImageInfo.makeN32Premul( 1024, 1024));
        Canvas canvas = surface.getCanvas();

        ParagraphStyle paragraphStyle = new ParagraphStyle()
                .setTextStyle(new TextStyle()
                        .setColor(Colors.RED)
                        .setFontSize(25)
                        .setTypeface(FontMgr.getDefault().matchFamilyStyle("Arial", FontStyle.NORMAL))
                )
                ;
        // ！这里必须设置这个默认的字体管理器，否则什么也渲染不出来！
        FontCollection fontCollection = new FontCollection()
                .setDefaultFontManager(FontMgr.getDefault());

        ParagraphBuilder builder = new ParagraphBuilder(paragraphStyle, fontCollection);

        builder.addText("[From.GetName]... From orbit, it appears so small.");
        builder.addText(" A world does not belong to its people; it is the people who belong to their world. " +
                "In returning here, we take another step towards reclaiming our past.");
        builder.addText("The trauma we experienced at the hands of our former masters can never be undone. " +
                "True healing will take generations. But today is a good day.");

        Paragraph paragraph = builder.build();
        paragraph.layout(1000);

        paragraph.paint(canvas, 0, 0);


        writerToPng("textArea.png", surface.makeImageSnapshot());

    }

    /**
     * 输出图像
     *
     * @param outPutPath 输出路径
     * @param image      图片对象
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
