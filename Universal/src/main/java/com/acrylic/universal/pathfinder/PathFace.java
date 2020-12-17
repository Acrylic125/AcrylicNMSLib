package com.acrylic.universal.pathfinder;

import org.bukkit.block.BlockFace;

/**
 * Why this?
 *
 * This class was intended for path finding {@link com.acrylic.universal.entityai.pathfinder.EntityPathfinder}
 *
 * The reason for this "Wrapper" is to allow for efficient
 * block face checks with the use of bitmasks.
 */
public enum PathFace {

    UP(0x00, BlockFace.UP),
    DOWN(0x00, BlockFace.DOWN),
    NORTH(0x01, BlockFace.NORTH),
    EAST(0x02, BlockFace.EAST),
    SOUTH(0x04, BlockFace.SOUTH),
    WEST(0x08, BlockFace.WEST),
    NORTH_EAST(BlockFace.NORTH_EAST, NORTH, EAST),
    NORTH_WEST(BlockFace.NORTH_WEST, NORTH, WEST),
    SOUTH_EAST(BlockFace.SOUTH_EAST, SOUTH, EAST),
    SOUTH_WEST(BlockFace.SOUTH_WEST, SOUTH, WEST);

    private final int bitMask;
    private final BlockFace face;

    PathFace(int bitMask, BlockFace face) {
        this.bitMask = bitMask;
        this.face = face;
    }

    PathFace(BlockFace blockFace, PathFace face1, PathFace face2) {
        this.face = blockFace;
        this.bitMask = face1.getBitMask() | face2.getBitMask();
    }

    public int getBitMask() {
        return bitMask;
    }

    public BlockFace getFace() {
        return face;
    }

    /**
     *
     * @param bitMask The current bitmask that is iterating
     *                through the path faces.
     *
     *                This is used to determine if NORTH_EAST,
     *                NORTH_WEST, SOUTH_EAST, or SOUTH_WEST, can
     *                be traversed with respect to it's parent Path
     *                Faces.
     *
     *                For example,
     *                if ONLY NORTH IS BLOCKED, the path finder
     *                CANNOT traverse through either NORTH_WEST or
     *                NORTH_EAST directly.
     *
     *                Similarly, if both NORTH and EAST are blocked,
     *                ONLY SOUTH WEST CAN be traversed.
     *
     * @return If the path face is blocked by it's parent.
     */
    public boolean isBlocked(int bitMask) {
        return (bitMask & this.bitMask) << this.bitMask != 0;
    }

    /**
     *
     * @param bitMask Adds this path face to the iterated
     *                bit mask.
     *
     *                (See #isBlocked for more information.)
     * @return The current bit mask of the iterated.
     */
    public int addToMask(int bitMask) {
        return this.bitMask | bitMask;
    }

    /** The order is important! **/
    public static final PathFace[] PATH_FACES = new PathFace[] {
        UP, DOWN, NORTH, EAST, SOUTH, WEST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST
    };

}
