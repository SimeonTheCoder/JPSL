import data.Vec2;
import data.Vec3;
import parser.JPSLProgram;

import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        LinkedHashMap<String, Vec2> args2 = new LinkedHashMap<>();
        LinkedHashMap<String, Vec3> args3 = new LinkedHashMap<>();

        boolean display = false;

        String out = null;
        String path = null;

        int threadCount = 1;

        for (String arg : args) {
            if(arg.equals("-d")) display = true;

            if(arg.startsWith("-o")) {
                out = arg.split("\\(")[1].split("\\)")[0];
            }

            if(arg.startsWith("-p")) {
                path = arg.split("\\(")[1].split("\\)")[0];
            }

            if(arg.startsWith("-a3")) {
                String argName = arg.split("\\(")[1].split("\\)")[0].split(",")[0];

                double a = Double.parseDouble(arg.split("\\(")[1].split("\\)")[0].split(",")[1]);
                double b = Double.parseDouble(arg.split("\\(")[1].split("\\)")[0].split(",")[2]);
                double c = Double.parseDouble(arg.split("\\(")[1].split("\\)")[0].split(",")[3]);

                args3.put(argName, new Vec3(a,b,c));
            }

            if(arg.startsWith("-a2")) {
                String argName = arg.split("\\(")[1].split("\\)")[0].split(",")[0];

                double a = Double.parseDouble(arg.split("\\(")[1].split("\\)")[0].split(",")[1]);
                double b = Double.parseDouble(arg.split("\\(")[1].split("\\)")[0].split(",")[2]);

                args2.put(argName, new Vec2(a,b));
            }

            if(arg.startsWith("-t")) {
                threadCount = Integer.parseInt(String.valueOf(arg.charAt(2)));
            }
        }

        args2.put("factor", new Vec2(.5, .5));

        JPSLProgram parser = new JPSLProgram(path, args2, args3, display, out, threadCount);

        parser.runThreaded();
    }
}