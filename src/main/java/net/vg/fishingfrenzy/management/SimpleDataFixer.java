package net.vg.fishingfrenzy.management;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.TaggedChoice;
import com.mojang.serialization.DynamicOps;
import net.minecraft.datafixer.TypeReferences;

public class SimpleDataFixer extends DataFix {
    private final String name;

    public SimpleDataFixer(String name, Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
        this.name = name;
    }

    @Override
    protected TypeRewriteRule makeRule() {
        TaggedChoice.TaggedChoiceType<String> inputType = (TaggedChoice.TaggedChoiceType<String>) getInputSchema().findChoiceType(TypeReferences.ENTITY);
        TaggedChoice.TaggedChoiceType<String> outputType = (TaggedChoice.TaggedChoiceType<String>) getOutputSchema().findChoiceType(TypeReferences.ENTITY);

        return fixTypeEverywhere(name, inputType, outputType, dynamicOps -> pair -> {
            String id = pair.getFirst();
            Type<?> type = inputType.types().get(id);
            return pair.mapSecond(value -> value); // no transformation, just passing through
        });
    }
}
