package com.kioea.www.textutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.kioea.www.textutil.DirtWordFilter;
import com.kioea.www.textutil.DirtWordFilterInit;

public class DirtTest {
	private static Logger logger = Logger.getLogger(DirtTest.class);
	private static List<String> ipCityList = new ArrayList<String>(100000);
	/**
	 * 初始化操作
	 */
	/*static {
		String line = null;
		try {
			Reader r = new InputStreamReader(DirtTest.class.getClassLoader().getResourceAsStream(
					"key.txt"), "UTF-8");
			BufferedReader br = new BufferedReader(r);

			while ((line = br.readLine()) != null) {
				ipCityList.add(line);
			}

			logger.info("装载ip_city数据成功:" + ipCityList.size());

			br.close();
			br = null;
		} catch (Exception ex) {
			logger.error("装载ip_city数据出错：", ex);
		}
	}*/


	public static void main(String[] args) throws IOException {
		// String word1 = "apple";
		// String text1 = "washington cut the apple tree";
		// Set<?> result1 = obj.findSingleWord(word1, text1);
		// System.out.println(result1);
		// System.out.println("===========================");
		//
		// String [] word2 = {"microsome", "cytochrome",
		// "cytochrome P450 activity", "gibberellic acid biosynthesis", "GA3",
		// "cytochrome P450", "oxygen binding", "AT5G25900.1", "protein", "RNA",
		// "gibberellin", "Arabidopsis", "ent-kaurene oxidase activity",
		// "inflorescence", "tissue"};
		// String text2 =
		// "The ga3 mutant of Arabidopsis is a gibberellin-responsive dwarf. We present data showing that the ga3-1 mutant is deficient in ent-kaurene oxidase activity, the first cytochrome P450-mediated step in the gibberellin biosynthetic pathway. By using a combination of conventional map-based cloning and random sequencing we identified a putative cytochrome P450 gene mapping to the same location as GA3. Relative to the progenitor line, two ga3 mutant alleles contained single base changes generating in-frame stop codons in the predicted amino acid sequence of the P450. A genomic clone spanning the P450 locus complemented the ga3-2 mutant. The deduced GA3 protein defines an additional class of cytochrome P450 enzymes. The GA3 gene was expressed in all tissues examined, RNA abundance being highest in inflorescence tissue.";
		// Set<?> result2 = obj.findWordsInArray(word2, text2);
		// System.out.println(result2);
		//		
		// System.out.println("===========================");
		//		
		// String filename1 = getResource("resources/key.txt");
		// String filename2 = getResource("resources/text.txt");
		// Set<?> result3 = obj.findWordsInFile(filename1, filename2);
		// System.out.println(result3);
		//		
		// DirtWordFilter dirtWordService = new
		// DirtWordFilter(getResource("resources/key.txt"));
		// dirtWordService.init();
		// //
		// System.out.println(dirtWordService.containsDirtWord(FileUtil.readText(Paths.get(getResource("resource/text.txt")))));
		// System.out.println(dirtWordService.containsDirtWord("11111111111111胡锦涛111111"));
		// System.out.println(dirtWordService.doFilter("1111111罢工1111111天安门1111洪志11"));
		// //
		// System.out.println(dirtWordService.doFilter(FileUtil.readText(Paths.get(getResource("resource/text.txt")))));

		String text = "11111和谐1111111习近，你的法轮111李洪志11，李洪志,11111和谐1111111习近，你的法轮111李洪志11，李洪志,11111和谐1111111习近，你的法轮111李洪志11，李洪志,11111和谐1111111习近，你的法轮111李洪志11，李洪志,11111和谐1111111习近，你的法轮111李洪志11，李洪志,11111和谐1111111习近，你的法轮111李洪志11，李洪志,11111和谐1111111习近，你的法轮111李洪志11，李洪志,11111和谐1111111习近，你的法轮111李洪志11，李洪志,11111和谐1111111习近，你的法轮111李洪志11，李洪志,11111和谐1111111习近，你的法轮111李洪志11，李洪志,11111和谐1111111习近，你的法轮111李洪志11，李洪志,11111和谐1111111习近，你的法轮111李洪志11，李洪志,11111和谐1111111习近，你的法轮111李洪志11，李洪志,11111和谐1111111习近，你的法轮111李洪志11，李洪志,11111和谐1111111习近，你的法轮111李洪志11，李洪志,11111和谐1111111习近，你的法轮111李洪志11，李洪志,11111和谐1111111习近，你的法轮111李洪志11，李洪志,11111和谐1111111习近，你的法轮111李洪志11，李洪志,1111111和谐1111111习近，你的法轮111李洪志11，李洪志,1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志1111111和谐1111111习近，你的法轮111李洪志11，李洪志";
//		String text = "和谐社会";
		
		
		Long startTime = System.currentTimeMillis();
		// System.out.println(dirtWordService2.containsDirtWord("11111111111111洪志111111"));
		// System.out.println(dirtWordService2.doFilter("1111111和谐1111111习近，你的法轮111李洪志11"));
		DirtWordFilter dirtWordService2 = new DirtWordFilter(getResource("resources/key1.txt"));
		dirtWordService2.init();
		System.out.println(
		dirtWordService2.getDirtWordSet(text));
		// );
		Long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);

		Long startTime1 = System.currentTimeMillis();
		
		String line = null;
		try {
			Reader r = new InputStreamReader(DirtTest.class.getClassLoader().getResourceAsStream(
					"key1.txt"), "UTF-8");
			BufferedReader br = new BufferedReader(r);

			while ((line = br.readLine()) != null) {
				ipCityList.add(line);
			}

			//logger.info("装载ip_city数据成功:" + ipCityList.size());

			br.close();
			br = null;
		} catch (Exception ex) {
			logger.error("装载ip_city数据出错：", ex);
		}
		
		Set<String> set =new HashSet<String>();
		for(String str : ipCityList){
			if(text.contains(str)){
				 set.add(str);
			}
		}
		System.out.println(set);
		Long endTime1 = System.currentTimeMillis();
		System.out.println(endTime1 - startTime1);
		Long startTime2 = System.currentTimeMillis();
		DirtWordFilterInit init = new DirtWordFilterInit();
		System.out.println(
				init.getDirtWordSet(text));
		// );
		Long endTime2 = System.currentTimeMillis();
		System.out.println(endTime2 - startTime2);
		System.out.println();
		
		String text1 = "1，李洪志,11111和谐1111111习近，你的法轮,1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮1，李洪志,11111和谐1111111习近，你的法轮" +
				"十月的的天气，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，经不起一点风吹雨打。街道两旁的杨树叶开始纷纷落下，小巷里的槐树也已经枯萎，在路的尽头飘然而落。慕婉婷一个人慢慢走着，抬着头透过枝叶的细缝望着远处的天空，十八年的时间好像什么都变了，好像什么都没变，总是觉得少了什么，也许很多光阴都已经物是人非了。" +
				"十月的的天气，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，经不起一点风吹雨打。街道两旁的杨树叶开始纷纷落下，小巷里的槐树也已经枯萎，在路的尽头飘然而落。慕婉婷一个人慢慢走着，抬着头透过枝叶的细缝望着远处的天空，十八年的时间好像什么都变了，好像什么都没变，总是觉得少了什么，也许很多光阴都已经物是人非了。" +
				"十月的的天气，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，经不起一点风吹雨打。街道两旁的杨树叶开始纷纷落下，小巷里的槐树也已经枯萎，在路的尽头飘然而落。慕婉婷一个人慢慢走着，抬着头透过枝叶的细缝望着远处的天空，十八年的时间好像什么都变了，好像什么都没变，总是觉得少了什么，也许很多光阴都已经物是人非了。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。街道两旁的杨树叶开始纷纷落下，小巷里的槐树也已经枯萎，在路的尽头飘然而落。慕婉婷一个人慢慢走着，抬着头透过枝叶的细缝望着远处的天空，十八年的时间好像什么都变了，好像什么都没变，总是觉得少了什么，也许很多光阴都已经物是人非了。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"十月的的毛泽东，早晚微凉，中午还是有些晒，微风吹过，很惬意，北方的小城四季分明，这个时节树叶已经纷纷老去，邓小平经不起一点风吹雨打。" +
				"";
		
		Long startTime3 = System.currentTimeMillis();
		System.out.println(
				dirtWordService2.getDirtWordSet(text1));
		// );
		Long endTime3 = System.currentTimeMillis();
		System.out.println(endTime3 - startTime3);
		
		Long startTime4 = System.currentTimeMillis();
		for(String str : ipCityList){
			if(text1.contains(str)){
				 set.add(str);
			}
		}
		System.out.println(set);
		Long endTime4 = System.currentTimeMillis();
		System.out.println(endTime4 - startTime4);
		
		Long startTime5 = System.currentTimeMillis();
		System.out.println(
				init.getDirtWordSet(text1));
		// );
		Long endTime5 = System.currentTimeMillis();
		System.out.println(endTime5 - startTime5);
		

	}

	private static String getResource(String name) throws IOException {
		return new File(new File("").getCanonicalFile(), name).getAbsolutePath();
	}

}
