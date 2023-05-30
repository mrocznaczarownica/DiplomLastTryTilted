package com.example.diplomlasttrytilted.auxiliaryClasses

import android.provider.BaseColumns

class DatabaseContract {
    // Классы, описывающие структуры таблиц в базе данных
    class CategoriesEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "Categories"
            const val COLUMN_NAME = "Name"
            const val COLUMN_ISSERVICE = "IsService"
        }
    }

    class UsersEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "Users"
            const val COLUMN_FIRST_NAME = "First_Name"
            const val COLUMN_LAST_NAME = "Last_Name"
            const val COLUMN_MIDDLE_NAME = "Middle_Name"
            const val COLUMN_PHONE_NUMBER = "Phone_Number"
            const val COLUMN_LOGIN = "Login"
            const val COLUMN_PASSWORD = "Password"
            const val COLUMN_ROL = "Rol"
            const val COLUMN_ISCLIENT = "IsClient"
        }
    }

    class CheckLineProductEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "Check_Line_Product"
            const val COLUMN_ID_PRODUCT = "Id_Product"
            const val COLUMN_ID_CHECK = "Id_Check"
            const val COLUMN_DATE = "Date"
            const val COLUMN_COUNT = "Count"
        }
    }

    class CheckLineTariffEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "Check_Line_Tariff"
            const val COLUMN_ID_TARIFF = "id_Tariff"
            const val COLUMN_ID_CHECK = "Id_Check"
            const val COLUMN_COUNT = "Count"
        }
    }

    class ProductInfoEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "Product_Info"
            const val COLUMN_INFO = "Info"
            const val COLUMN_ID_CHECK_LINE_PRODUCT = "Id_Check_Line_Product"
            const val COLUMN_IMAGE = "Image"
        }
    }

    class ChecksEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "Checks"
            const val COLUMN_DATE = "Date"
            const val COLUMN_ID_USER = "id_User"
            const val COLUMN_SUM = "Sum"
        }
    }

    class TariffPositionEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "Tariff_Position"
            const val COLUMN_ID_PRODUCT = "Id_Product"
            const val COLUMN_ID_TARIFF = "Id_Tariff"
        }
    }

    class TariffEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "Tariff"
            const val COLUMN_NAME = "Name"
            const val COLUMN_DESCRIPTION = "Description"
            const val COLUMN_PRICE = "Price"
            const val COLUMN_IMAGE = "Image"
        }
    }

    class ConsultationEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "Consultation"
            const val COLUMN_ID_CLIENT = "Id_Client"
            const val COLUMN_DATE = "Date"
        }
    }

    class ProductEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "Product"
            const val COLUMN_ID_CATEGORY = "Id_Category"
            const val COLUMN_NAME = "Name"
            const val COLUMN_DESCRIPTION = "Description"
            const val COLUMN_PRICE = "Price"
            const val COLUMN_COUNTSTOCK = "CountStock"
            const val COLUMN_IS_SERVICE = "Is_Service"
            const val COLUMN_SALE_PERCENT = "Sale_Percent"
            const val COLUMN_IMAGE = "Image"
        }
    }
}