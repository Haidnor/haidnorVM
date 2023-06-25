package haidnor.jvm.instruction.loads;

import haidnor.jvm.instruction.Instruction;
import haidnor.jvm.runtime.Frame;
import haidnor.jvm.runtime.StackValue;
import haidnor.jvm.util.CodeStream;
import org.apache.bcel.Const;


public class DLOAD extends Instruction {

    private final int index;

    public DLOAD(CodeStream codeStream) {
        super(codeStream);
        this.index = codeStream.readUnsignedByteOperand(this);
    }

    @Override
    public void execute(Frame frame) {
        double value = frame.slotGetDouble(index);
        frame.push(new StackValue(Const.T_DOUBLE, value));
    }

}
