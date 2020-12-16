/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2011-2020 Broad Institute, Aiden Lab, Rice University, Baylor College of Medicine
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package juicebox.tools.clt;

import juicebox.HiC;
import juicebox.data.ChromosomeHandler;
import juicebox.data.Dataset;
import juicebox.data.HiCFileTools;
import juicebox.data.MatrixZoomData;
import juicebox.data.basics.Chromosome;
import juicebox.tools.utils.common.MatrixTools;
import juicebox.windowui.HiCZoom;
import juicebox.windowui.NormalizationHandler;
import org.apache.commons.math.linear.RealMatrix;

import java.util.ArrayList;
import java.util.List;


/**
 * Created for testing multiple CLTs at once
 * Basically scratch space
 */
class AggregateProcessing {


    public static void main(String[] argv) throws Exception {

        List<String> files = new ArrayList<>();
        files.add("/Users/mshamim/Desktop/hicfiles/GM12878_intact_18.7B_8.15.20_30.hic");

        Dataset ds = HiCFileTools.extractDatasetForCLT(files, true);
        ChromosomeHandler handler = ds.getChromosomeHandler();
        Chromosome chrom1 = handler.getChromosomeFromName("10");
        HiCZoom zoom = new HiCZoom(HiC.Unit.BP, 5000);
        MatrixZoomData zd = HiCFileTools.getMatrixZoomData(ds, chrom1, chrom1, zoom);


        //int pos1 = 64000000/5000;
        //int pos2 = 69000000/5000 + 1;

        int pos1 = 70000000 / 5000;
        int pos2 = 80000000 / 5000 + 1;


        int width = 2000;
        RealMatrix matrix = HiCFileTools.extractLocalBoundedRegion(zd, pos1, pos2, pos1, pos2, width, width, NormalizationHandler.SCALE, true);
        String outloc = "/Users/mshamim/Desktop/stripes/chr1_intact_70M_80M.npy";
        MatrixTools.saveMatrixTextNumpy(outloc, matrix.getData());


/*
        String[] strings = new String[]{"pre", //"-c", "1", //"--verbose",
                "/Users/muhammad/JuiceboxMain/data/test.txt.gz",
                "/Users/muhammad/Dropbox (Lab at Large)/testV9/test_new9-15.hic",
                "hg19"};
    
        HiCTools.main(strings);
        /*
        strings = new String[]{"grind",
                "-k", "KR", "-r", "25000",// "5000,10000,25000",
                "--stride", "1500", "-c", "4,5",
                "--dense-labels", "--distort",
                "/Users/muhammad/Desktop/local_hic_files/HIC053_30.hic",
                "null", "2000,12,100",
                "/Users/muhammad/Desktop/deeplearning/testing/distortion_bank_4_5_debug_version"};

        for (int k = 1; k < 2; k++) {
            UNIXTools.makeDir("/Users/muhammad/Desktop/test_pre/multi_test_finalscale" + k);
            strings = new String[]{"pre", "--threads", "" + k, "--mndindex",
                    "/Users/muhammad/Desktop/test_pre/indices.txt", "--skip-intra-frag", //"-n",
                    //"/Users/muhammad/JuiceboxAgain/data/test.txt.gz",
                    "/Users/muhammad/Desktop/test_pre/test.txt",
                    "/Users/muhammad/Desktop/test_pre/multi_test_finalscale" + k + "/test" + k + ".hic",
                    "hg19"};

            HiCTools.main(strings);
            System.gc();
        }

        // load the model

        /*
        String simpleMlp = "/Users/muhammad/Desktop/deeplearning/models/Clean64DistortionDiffHalfLocalizerV0BinCross.h5";
        MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(simpleMlp);


        // make a random sample
        int inputs = 10;
        INDArray features = Nd4j.zeros(inputs);
        for (int i=0; i<inputs; i++) {
            features.putScalar(new int[]{i}, Math.random() < 0.5 ? 0 : 1);
        }
// get the prediction
        //double prediction = model.output(features).getDouble(0);

         */



    }
}
