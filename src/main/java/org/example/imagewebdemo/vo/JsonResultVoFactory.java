package org.example.imagewebdemo.vo;

/**
 * Create By: Jimmy Song
 * Create At: 2022-08-04 18:21
 */
public class JsonResultVoFactory {
    private final static JsonResultVo inputErrorResult = new JsonResultVo<>(ResultCodeEnum.INPUT_ERROR.code, ResultCodeEnum.INPUT_ERROR.message, null);
    private final static JsonResultVo inputParameterErrorResult = new JsonResultVo<>(ResultCodeEnum.INPUT_PARAMETER_ERROR.code, ResultCodeEnum.INPUT_PARAMETER_ERROR.message, null);
    private final static JsonResultVo inputHeaderErrorResult = new JsonResultVo<>(ResultCodeEnum.INPUT_HEADER_ERROR.code, ResultCodeEnum.INPUT_HEADER_ERROR.message, null);
    private final static JsonResultVo unknownErrorResult = new JsonResultVo<>(ResultCodeEnum.UNKNOWN.code, ResultCodeEnum.UNKNOWN.message, null);

    public static JsonResultVo inputErrorResult(){
        return inputErrorResult;
    }
    public static JsonResultVo unknownErrorResult(){ return unknownErrorResult;}
    public static JsonResultVo inputParameterErrorResult(){ return inputParameterErrorResult;}
    public static JsonResultVo inputHeaderErrorResult(){ return inputHeaderErrorResult;}
}
