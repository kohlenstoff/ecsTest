package ecsTest;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Grid {

    private Gridtype gridType;
    private int tilesX;
    private int tilesY;
    private int tileWidth;
    private int tileHeight;

    private int maxWidth;
    private int maxHeight;
    private float maxWidthDiagonale;
    private float maxHeightDiagonale;

    public Array<Integer> xPoints = new Array<>();
    public Array<Integer> yPoints = new Array<>();

    final Matrix4 rotationMatrix = new Matrix4();

    public enum Gridtype {
        NORMAL (1),
        ISOMETRIC (2);

        private final float ratio;

        private Gridtype(float ratio){
            this.ratio = ratio;
        }
        public float getRatio(){
            return this.ratio;
        }
    }

    public Grid(){
        this(Gridtype.NORMAL);
    }
    public Grid(Gridtype type){
        this.gridType = type;
    }
    public Grid(Gridtype type, int tilesX, int tilesY, int tileWidth, int tileHeight){
        this.gridType = type;
        this.tilesX = tilesX;
        this.tilesY = tilesY;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        calculate();
    }

    /**
     * Needs to be called after changes to tile amount and tile size
     */
    public void calculate(){
        //calculation
        maxWidth = tilesX*tileWidth;
        maxHeight = tilesY*tileHeight;
        maxWidthDiagonale = (float)Math.sqrt( (maxWidth * maxWidth)+(maxHeight * maxHeight) );
        maxHeightDiagonale = maxWidthDiagonale /this.gridType.getRatio();
        float angle = 0;
        angle = (float)Math.atan( (maxHeightDiagonale /2)/(maxWidthDiagonale /2) );
        angle = (float)Math.toDegrees(angle);
        angle = (90-angle)*-1;

        switch(gridType){
            case NORMAL:    angle = -90; break;
            case ISOMETRIC: angle = -60; break;
        }

        //rotation for the areaEntity background
        rotationMatrix.setToRotation(new Vector3(1,0,0), angle);
    }

    public void setGridType(Gridtype gridType){
        this.gridType = gridType;
    }

    public void boundaryGridPoints(){
        for( int x=0; x < tilesX; x++ ){
            xPoints.add( x*tileWidth );
        }
        for( int y=0; y < tilesX; y++ ){
            yPoints.add( y*tileHeight );
        }
    }

    public void setTiles(int amount){
        this.tilesX = amount;
        this.tilesY = amount;
    }
    public void setTilesX(int tilesX) {
        this.tilesX = tilesX;
    }

    public void setTilesY(int tilesY) {
        this.tilesY = tilesY;
    }

    public void setTileSize(int length){
        this.tileWidth = length;
        this.tileHeight = length;
    }
    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public float getMaxWidthDiagonale() {
        return maxWidthDiagonale;
    }

    public float getMaxHeightDiagonale() {
        return maxHeightDiagonale;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }
}
