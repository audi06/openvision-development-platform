FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_sh4 += "\
    file://glibc-fix-with-old-kernel.patch \
    file://sh4-trap.patch \
"

SRC_URI_append_cube += "\
    file://glibc-fix-with-old-kernel.patch \
"

SRC_URI_append_su980 += "\
    file://glibc-fix-with-old-kernel.patch \
"

