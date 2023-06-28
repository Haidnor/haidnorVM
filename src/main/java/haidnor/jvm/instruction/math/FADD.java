package haidnor.jvm.instruction.math;

import haidnor.jvm.instruction.Instruction;
import haidnor.jvm.runtime.Frame;
import haidnor.jvm.runtime.StackValue;
import haidnor.jvm.util.CodeStream;
import org.apache.bcel.Const;

public class FADD extends Instruction {

    public FADD(CodeStream codeStream) {
        super(codeStream);
    }

    @Override
    public void execute(Frame frame) {
        StackValue value1 = frame.pop();
        StackValue value2 = frame.pop();
        float result = (float) value1.getValue() + (float) value2.getValue();
        frame.push(new StackValue(Const.T_FLOAT, result));
    }

}
