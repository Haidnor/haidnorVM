package haidnor.jvm.test;

import haidnor.jvm.classloader.ClassLoader;
import haidnor.jvm.core.JavaExecutionEngine;
import haidnor.jvm.rtda.heap.Klass;
import haidnor.jvm.rtda.heap.KlassMethod;
import haidnor.jvm.rtda.metaspace.Metaspace;
import haidnor.jvm.runtime.JvmThread;
import haidnor.jvm.test.instruction.Array;
import haidnor.jvm.test.instruction.DO_WHILE;
import haidnor.jvm.test.instruction.InnerClass;
import haidnor.jvm.test.instruction.math.ISUB;
import haidnor.jvm.test.instruction.math.LSUB;
import haidnor.jvm.test.instruction.references.INVOKEINTERFACE;
import haidnor.jvm.test.instruction.references.NEW;
import haidnor.jvm.util.JavaClassUtil;
import haidnor.jvm.util.JvmThreadHolder;
import lombok.SneakyThrows;
import org.junit.Test;

public class TestJVM {

    @SneakyThrows
    public static void runMainClass(java.lang.Class<?> mainClass) {
        ClassLoader bootClassLoader = new ClassLoader("ApplicationClassLoader");
        Klass mainMeteKlass = bootClassLoader.loadClass(mainClass.getName().replace('.', '/'));
        KlassMethod mainKlassMethod = JavaClassUtil.getMainMethod(mainMeteKlass);
        Metaspace.registerJavaClass(mainMeteKlass);
        JvmThreadHolder.set(new JvmThread());

        JavaExecutionEngine.callMainStaticMethod(mainKlassMethod);
    }

    @Test
    public void test_NEW() throws Exception {
        runMainClass(NEW.class);
    }

    @Test
    public void test_ISUB() throws Exception {
        runMainClass(ISUB.class);
    }

    @Test
    public void test_LSUB() throws Exception {
        runMainClass(LSUB.class);
    }

    @Test
    public void test_1() throws Exception {
        runMainClass(InnerClass.class);
    }

    @Test
    public void test_DOWHILE() throws Exception {
        runMainClass(DO_WHILE.class);
    }
    @Test
    public void test_Array() throws Exception {
        runMainClass(Array.class);
    }
    @Test
    public void test_INVOKEINTERFACE() throws Exception {
        runMainClass(INVOKEINTERFACE.class);
    }
}