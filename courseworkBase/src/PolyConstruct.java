/**
 * @ClassName: PolyConstruct
 * @Author: cuny
 * @Date: 2022/4/12 10:30
 * @Description: 第四章作业
 **/


class Glyph {

    void draw() { System.out.println("Glyph.draw()"); }

    Glyph() {

        draw();

    }

}

class RoundGlyph extends Glyph {

    private int radius = 1;

    RoundGlyph(int r) {

        radius = r;

        System.out.println("RG.RoundGlyph(), radius = " + radius);

    }

    void draw() { System.out.println("RG.draw(), radius = " + radius); }

}

public class PolyConstruct {

    public static void main(String[] args) {

        new RoundGlyph(5);

    }

}