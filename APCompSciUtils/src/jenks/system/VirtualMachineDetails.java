package jenks.system;

import java.lang.reflect.Field;

//import sun.misc.Unsafe;

public class VirtualMachineDetails {
	
	private static boolean is64bit = true;
	
	/*public static void printAddresses(String label, Object... objects) {
	    System.out.print(label + ": 0x");
	    long last = 0;
	    Unsafe unsafe = getUnsafe();
	    int offset = unsafe.arrayBaseOffset(objects.getClass());
	    int scale = unsafe.arrayIndexScale(objects.getClass());
	    switch (scale) {
	    case 4:
	        long factor = is64bit ? 8 : 1;
	        final long i1 = (unsafe.getInt(objects, offset) & 0xFFFFFFFFL) * factor;
	        System.out.print(Long.toHexString(i1));
	        last = i1;
	        for (int i = 1; i < objects.length; i++) {
	            final long i2 = (unsafe.getInt(objects, offset + i * 4) & 0xFFFFFFFFL) * factor;
	            if (i2 > last)
	                System.out.print(", +" + Long.toHexString(i2 - last));
	            else
	                System.out.print(", -" + Long.toHexString( last - i2));
	            last = i2;
	        }
	        break;
	    case 8:
	        throw new AssertionError("Not supported");
	    }
	    System.out.println();
	}
	
	@SuppressWarnings("restriction")
    private static Unsafe getUnsafe() {
        try {

            Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
            singleoneInstanceField.setAccessible(true);
            return (Unsafe) singleoneInstanceField.get(null);
        } catch (IllegalArgumentException | SecurityException |
        		NoSuchFieldException |IllegalAccessException e) {
            e.printStackTrace(System.err);
            return null;
        }
    }*/
}
