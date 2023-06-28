package haidnor.jvm.instruction.math;

import haidnor.jvm.instruction.Instruction;
import haidnor.jvm.runtime.Frame;
import haidnor.jvm.runtime.StackValue;
import haidnor.jvm.util.CodeStream;
import org.apache.bcel.Const;

public class DADD extends Instruction {

    public DADD(CodeStream codeStream) {
        super(codeStream);
    }

    @Override
    public void execute(Frame frame) {
        StackValue value1 = frame.pop();
        StackValue value2 = frame.pop();
        double result = (double) value1.getValue() + (double) value2.getValue();
        frame.push(new StackValue(Const.T_DOUBLE, result));
    }

}
