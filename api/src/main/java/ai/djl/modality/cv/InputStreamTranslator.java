/*
 * Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ai.djl.modality.cv;

import ai.djl.ndarray.NDList;
import ai.djl.translate.Translator;
import ai.djl.translate.TranslatorContext;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * Built-in {@code Translator} that provides image pre-processing from {@code InputStream}.
 *
 * @param <T> the output object type
 */
public class InputStreamTranslator<T> implements Translator<InputStream, T> {

    private Translator<BufferedImage, T> translator;

    /**
     * Creates a {@code UrlTranslator} instance.
     *
     * @param translator a {@code Translator} that can process image
     */
    public InputStreamTranslator(Translator<BufferedImage, T> translator) {
        this.translator = translator;
    }

    /** {@inheritDoc} */
    @Override
    public NDList processInput(TranslatorContext ctx, InputStream input) throws Exception {
        BufferedImage image = ImageIO.read(input);
        return translator.processInput(ctx, image);
    }

    /** {@inheritDoc} */
    @Override
    public T processOutput(TranslatorContext ctx, NDList list) throws Exception {
        return translator.processOutput(ctx, list);
    }
}
