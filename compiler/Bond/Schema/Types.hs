-- Copyright (c) Microsoft. All rights reserved.
-- Licensed under the MIT license. See LICENSE file in the project root for full license information.

{-# LANGUAGE OverloadedStrings, RecordWildCards, DeriveGeneric #-}

module Bond.Schema.Types
    ( Attribute(..)
    , Bond(..)
    , Constant(..)
    , Constraint(..)
    , Declaration(..)
    , Default(..)
    , Field(..)
    , Import(..)
    , Language(..)
    , Modifier(..)
    , Namespace(..)
    , QualifiedName
    , Type(..)
    , TypeParam(..)
    ) where

import Data.Word
import Bond.Util
import GHC.Generics (Generic)

type QualifiedName = [String]

data Modifier = Optional | Required | RequiredOptional
    deriving (Eq, Generic)

data Type =
    BT_Int8 | BT_Int16 | BT_Int32 | BT_Int64 |
    BT_UInt8 | BT_UInt16 | BT_UInt32 | BT_UInt64 |
    BT_Float | BT_Double |
    BT_Bool |
    BT_String | BT_WString |
    BT_MetaName | BT_MetaFullName |
    BT_Blob |
    BT_Maybe Type |
    BT_List Type |
    BT_Vector Type |
    BT_Nullable Type |
    BT_Set Type |
    BT_Map Type Type |
    BT_Bonded Type |
    BT_IntTypeArg Int |
    BT_TypeParam TypeParam |
    BT_UserDefined Declaration [Type]
    deriving (Eq, Generic)

data Default =
    DefaultBool Bool |
    DefaultInteger Integer |
    DefaultFloat Double |
    DefaultString String |
    DefaultEnum String |
    DefaultNothing
    deriving (Eq, Generic)

data Attribute =
    Attribute
        { attrName :: QualifiedName         -- attribute name
        , attrValue :: String               -- value
        }
    deriving (Eq, Generic)

data Field =
    Field
        { fieldAttributes :: [Attribute]    -- zero or more attributes
        , fieldOrdinal :: Word16            -- ordinal
        , fieldModifier :: Modifier         -- field modifier
        , fieldType :: Type                 -- type
        , fieldName :: String               -- field name
        , fieldDefault :: Maybe Default     -- optional default value
        }
    deriving (Eq, Generic)

data Constant =
    Constant
        { constantName :: String            -- enum constant name
        , constantValue :: Maybe Int        -- optional value
        }
    deriving (Eq, Generic)

data Constraint = Value
    deriving (Eq, Generic)

instance Show Constraint where
    show Value = ": value"

data TypeParam =
    TypeParam
        { paramName :: String
        , paramConstraint :: Maybe Constraint
        }
    deriving (Eq, Generic)

instance Show TypeParam where
    show TypeParam {..} = paramName ++ optional show paramConstraint

data Declaration =
    Struct
        { declNamespaces :: [Namespace]     -- namespace(s) in which the struct is declared
        , declAttributes :: [Attribute]     -- zero or more attributes
        , declName :: String                -- struct identifier
        , declParams :: [TypeParam]         -- type parameters for generics
        , structBase :: Maybe Type          -- optional base struct
        , structFields :: [Field]           -- zero or more fields
        }
    |
    Enum
        { declNamespaces :: [Namespace]     -- namespace(s) in which the enum is declared
        , declAttributes :: [Attribute]     -- zero or more attributes
        , declName :: String                -- enum identifier
        , enumConstants :: [Constant]       -- one or more constant values
        }
    |
    Forward
        { declNamespaces :: [Namespace]     -- namespace(s) in which the struct is declared
        , declName :: String                -- struct identifier
        , declParams :: [TypeParam]         -- type parameters for generics
        }
    |
    Alias
        { declNamespaces :: [Namespace]     -- namespace(s) in which the alias is declared
        , declName :: String                -- alias identifier
        , declParams :: [TypeParam]         -- type parameters for generics
        , aliasType :: Type                 -- aliased type
        }
    deriving (Eq, Generic)

showTypeParams :: [TypeParam] -> String
showTypeParams = angles . sepBy ", " show

instance Show Declaration where
    show Struct {..} = "struct " ++ declName ++ showTypeParams declParams
    show Enum {..} = "enum " ++ declName
    show Forward {..} = "struct declaration " ++ declName ++ showTypeParams declParams
    show Alias {..} = "alias " ++ declName ++ showTypeParams declParams

data Import = Import FilePath
    deriving (Eq, Generic)

data Language = Cpp | Cs | CSharp | Java
    deriving (Eq, Generic)

data Namespace =
    Namespace
        { nsLanguage :: Maybe Language
        , nsName :: QualifiedName
        }
    deriving (Eq, Generic)

data Bond =
    Bond
        { bondImports :: [Import]
        , bondNamespaces :: [Namespace]
        , bondDeclarations :: [Declaration]
        }
    deriving (Eq, Generic)

