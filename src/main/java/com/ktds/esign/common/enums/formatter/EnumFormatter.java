package com.ktds.esign.common.enums.formatter;

import com.ktds.esign.common.annos.enums.*;
import com.ktds.esign.common.enums.*;
import org.springframework.stereotype.Component;

/**
 * DTO to Entity Enum Type Mapping
 * Entity to DTO Enum Code Mapping
 * Use these in mapstruct class when ENUM String does not equal to database code exactly!
 */
@Component
@RootEnumMapper
public class EnumFormatter {

    /**
     * enum: FormType
     */
    @FormTypeMapper // String(code) -> Enum(type)
    public static FormType getFormTypeFromCode(String code) {
        return FormType.getTypeFromCode(code);
    }

    @FormCodeMapper // Enum(type) -> String(code)
    public static String getFormCodeFromType(FormType type) {
        return FormType.getCodeFromType(type);
    }

    @FormDescMapper // Enum(type) -> String(desc)
    public static String getFormDescFromType(FormType type) {
        return FormType.getDescFromType(type);
    }

    /**
     * enum: ContentsType
     */
    @ContentsTypeMapper // String(code) -> Enum(type)
    public static ContentsType getContentsTypeFromCode(String code) {
        return ContentsType.getTypeFromCode(code);
    }

    @ContentsCodeMapper // Enum(type) -> String(code)
    public static String getContentsCodeFromType(ContentsType type) {
        return ContentsType.getCodeFromType(type);
    }

    @ContentsDescMapper // Enum(type) -> String(desc)
    public static String getContentsDescFromType(ContentsType type) {
        return ContentsType.getDescFromType(type);
    }

    /**
     * enum: NotiType
     */
    @NotiTypeMapper // String(code) -> Enum(type)
    public static NotiType getNotiTypeFromCode(String code) {
        return NotiType.getTypeFromCode(code);
    }

    @NotiCodeMapper // Enum(type) -> String(code)
    public static String getNotiCodeFromType(NotiType type) {
        return NotiType.getCodeFromType(type);
    }

    @NotiDescMapper // Enum(type) -> String(desc)
    public static String getNotiDescFromType(NotiType type) {
        return NotiType.getDescFromType(type);
    }

    /**
     * enum: NotiDirectType
     */
    @NotiDirectTypeMapper // String(code) -> Enum(type)
    public static NotiDirectType getNotiDirectTypeFromCode(String code) {
        return NotiDirectType.getTypeFromCode(code);
    }

    @NotiDirectCodeMapper // Enum(type) -> String(code)
    public static String getNotiDirectCodeFromType(NotiDirectType type) {
        return NotiDirectType.getCodeFromType(type);
    }

    @NotiDirectDescMapper // Enum(type) -> String(desc)
    public static String getNotiDirectDescFromType(NotiDirectType type) {
        return NotiDirectType.getDescFromType(type);
    }

    /**
     * enum: ProgsSttusType
     */
    @ProgsSttusTypeMapper // String(code) -> Enum(type)
    public static ProgsSttusType getProgsSttusTypeFromCode(String code) {
        return ProgsSttusType.getTypeFromCode(code);
    }

    @ProgsSttusCodeMapper // Enum(type) -> String(code)
    public static String getProgsSttusCodeFromType(ProgsSttusType type) {
        return ProgsSttusType.getCodeFromType(type);
    }

    @ProgsSttusDescMapper // Enum(type) -> String(desc)
    public static String getProgsSttusDescFromType(ProgsSttusType type) {
        return ProgsSttusType.getDescFromType(type);
    }

    /**
     * enum: RoleType
     */
    @RoleTypeMapper // String(code) -> Enum(type)
    public static RoleType getRoleTypeFromCode(String code) {
        return RoleType.getTypeFromCode(code);
    }

    @RoleCodeMapper // Enum(type) -> String(code)
    public static String getRoleCodeFromType(RoleType type) {
        return RoleType.getCodeFromType(type);
    }

    @RoleDescMapper // Enum(type) -> String(desc)
    public static String getRoleDescFromType(RoleType type) {
        return RoleType.getDescFromType(type);
    }

    /**
     * enum: UserType
     */
    @UserTypeMapper // String(code) -> Enum(type)
    public static UserType getUserTypeFromCode(String code) {
        return UserType.getTypeFromCode(code);
    }

    @UserCodeMapper // Enum(type) -> String(code)
    public static String getUserCodeFromType(UserType type) {
        return UserType.getCodeFromType(type);
    }

    @UserDescMapper // Enum(type) -> String(desc)
    public static String getUserDescFromType(UserType type) {
        return UserType.getDescFromType(type);
    }

}
