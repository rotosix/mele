package com.mele.pinyin.service;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PinyinService {
	private Logger log = LoggerFactory.getLogger(PinyinService.class);

	public String getPinyin(String sentence) {
		StringBuilder sb = new StringBuilder();

		HanyuPinyinOutputFormat pin = new HanyuPinyinOutputFormat();
		pin.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		pin.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		char[] ar = sentence.toCharArray();
		for (int i = 0; i < ar.length; i++) {

			try {
				String[] ret = PinyinHelper.toHanyuPinyinStringArray(ar[i], pin);
				if ((ret != null) && (ret.length > 0)) {
					sb.append(ret[0]);
				} else {
					sb.append(ar[i]);
				}
			} catch (Exception e) {
				log.error("getPinyin sentence:"+sentence+" bad character:"+ar[i]+" exception:", e);
			}
		}

		String pinyinSentence = sb.toString();		
		return pinyinSentence;
	}

	public List<String> getPinyin(List<String> phrases) {
		List<String> pinyinPhrases = new ArrayList<String>();

		HanyuPinyinOutputFormat pin = new HanyuPinyinOutputFormat();
		pin.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		pin.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		StringBuilder sb = new StringBuilder();
		for (String phrase : phrases) {
			sb.setLength(0);

			char[] ar = phrase.toCharArray();
			for (int i = 0; i < ar.length; i++) {

				try {
					String[] ret = PinyinHelper.toHanyuPinyinStringArray(ar[i], pin);
					if ((ret != null) && (ret.length > 0)) {
						sb.append(ret[0]);
					} else {
						sb.append(ar[i]);
					}
				} catch (Exception e) {
					log.error("getPinyin sentence:"+phrase+" bad character:"+ar[i]+" exception:", e);

					sb.setLength(0);
					break;
				}
			}

			String pinyinPhrase = sb.toString();
			if (!StringUtils.isEmpty(pinyinPhrase) && (!pinyinPhrase.equals(phrase))) {
				pinyinPhrases.add(pinyinPhrase);
			}
		}

		return pinyinPhrases;
	}
}
