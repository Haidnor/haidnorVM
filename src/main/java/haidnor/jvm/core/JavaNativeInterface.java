package haidnor.jvm.core;


import haidnor.jvm.runtime.Frame;
import haidnor.jvm.runtime.JvmThread;
import haidnor.jvm.runtime.StackValue;
import haidnor.jvm.util.JvmThreadHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.classfile.LocalVariable;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.Utility;

@Slf4j
public class JavaNativeInterface {

    public static void callStaticMethod(Method method) {
        JvmThread jvmThread = JvmThreadHolder.get();
        Frame frame = new Frame(jvmThread, method);
        jvmThread.push(frame);
        Interpreter.executeFrame(frame);
    }

    public static void callStaticMethod(Frame lastFrame, Method method) {
        JvmThread jvmThread = JvmThreadHolder.get();
        Frame newFrame = new Frame(jvmThread, method);

        String signature = method.getSignature();
        String[] argumentTypes = Utility.methodSignatureArgumentTypes(signature);

        // 静态方法调用传参
        // 将上一个栈帧操作数栈中数据弹出,存入下一个栈帧的局部变量表中
        for (int i = argumentTypes.length - 1; i >= 0; i--) {
            LocalVariable[] localVariableTable = method.getLocalVariableTable().getLocalVariableTable();
            LocalVariable localVariable = localVariableTable[i];
            int slotIndex = localVariable.getIndex();
            StackValue stackValue = lastFrame.pop();
            newFrame.slotSet(slotIndex, stackValue);
        }
        jvmThread.push(newFrame);
        Interpreter.executeFrame(newFrame);
    }

}
