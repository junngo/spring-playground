package com.hello.springplayground.toby.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    /**
     * 템플릿: 공통된 내용은 템플릿으로 분리, 콜백을 받아서 처리 (콜백은 인터페이스를 활용하여 규정)
     * 파일 라인을 읽는 템플릿 -> 기능은 콜백에서 정의
     *
     * @param filepath
     * @param callback
     * @param initVal
     * @return
     * @throws IOException
     */
    public Integer lineReadTemplate(String filepath, LineCallback callback, int initVal) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filepath));
            Integer result = initVal;
            String line = null;
            while ((line = br.readLine()) != null) {
                result = callback.doSomethingWithLine(line, result);
            }
            return result;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * 최초 덧셈 기능 추가
     * -> **이후에 곱셈 기능이 추가 된다면** 아래 예외 처리는 템플릿/메소드 패턴으로
     *      중복을 제거하면서 리팩토링 가능
     * 
     * @param filepath
     * @return
     * @throws IOException
     */
    public Integer calcSum(String filepath) throws IOException {
        LineCallback sumCallback = new LineCallback() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value + Integer.valueOf(line);
            }
        };
        return lineReadTemplate(filepath, sumCallback, 0);
    }

    public Integer calcMultiply(String filepath) throws IOException {
        LineCallback multiplyCallback = new LineCallback() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value * Integer.valueOf(line);
            }
        };
        return lineReadTemplate(filepath, multiplyCallback, 1);
    }
}
