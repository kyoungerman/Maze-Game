import java.awt.geom.Line2D;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

public class MazeReader extends FileInputStream {
    private boolean finished = false;

    // This hash map is an association from a tile's ID to an
    // array of the lines on that tile.
    private HashMap<Integer, ArrayList<Line2D.Float>> pieces;

    private int bytesToInt(byte[] input) {
        ByteBuffer buffer = ByteBuffer.wrap(input);
        return buffer.getInt();
    }

    private float bytesToFloat(byte[] input) {
        ByteBuffer buffer = ByteBuffer.wrap(input);
        return buffer.getFloat();
    }

    private int readInt() throws IOException {
        if (finished)
            return -1;

        byte[] result = new byte[4];
        int readResult = read(result);

        if (readResult != -1) {
            return bytesToInt(result);
        }

        finished = true;
        return -1;
    }

    private float readFloat() throws IOException {
        if (finished)
            return -1;

        byte[] result = new byte[4];
        int readResult = read(result);

        if (readResult != -1) {
            return bytesToFloat(result);
        }

        finished = true;
        return -1;
    }

    private void readPiece() throws IOException {
        int id = readInt();
        int numLines = readInt();

        ArrayList<Line2D.Float> lines = new ArrayList<>();

        for (int i = 0; i < numLines; i++) {
            ArrayList<Float> values = new ArrayList<>();

            for (int j = 0; j < 4; j++) {
                values.add(Float.valueOf(readFloat()));
            }

            lines.add(new Line2D.Float(values.get(0), values.get(1),
                    values.get(2), values.get(3)));
        }

        pieces.put(Integer.valueOf(id), lines);
    }

    public MazeReader(String fileName) throws IOException {
        super(fileName);

        pieces = new HashMap<>();

        int numPieces = readInt(); // should be 16

        for (int i = 0; i < numPieces; i++) {
            readPiece();
        }
    }

    public ArrayList<Line2D.Float> getPiece(int id) {
        return pieces.get(Integer.valueOf(id));
    }
}
