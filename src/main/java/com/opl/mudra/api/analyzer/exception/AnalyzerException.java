package com.opl.mudra.api.analyzer.exception;

public class AnalyzerException extends Exception{

        private static final long serialVersionUID = 1L;

        public AnalyzerException() {
            super();
        }

        public AnalyzerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }

        public AnalyzerException(String message, Throwable cause) {
            super(message, cause);
        }

        public AnalyzerException(String message) {
            super(message);
        }

        public AnalyzerException(Throwable cause) {
            super(cause);
        }

}

